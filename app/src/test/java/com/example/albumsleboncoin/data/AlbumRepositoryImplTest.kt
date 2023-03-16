package com.example.albumsleboncoin.data

import com.example.albumsleboncoin.data.datasource.AlbumApiDataSource
import com.example.albumsleboncoin.data.datasource.AlbumLocalDataSource
import com.example.albumsleboncoin.data.local.database.AlbumEntity
import com.example.albumsleboncoin.domain.Album
import com.example.albumsleboncoin.mapper.AlbumDtoToAlbumEntityMapper
import com.example.albumsleboncoin.mapper.AlbumEntityToAlbumMapper
import com.example.common.resource.Resource
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class AlbumRepositoryImplTest {
    private lateinit var albumApiDataSource: AlbumApiDataSource
    private lateinit var albumLocalDataSource: AlbumLocalDataSource
    private lateinit var albumEntityToAlbumMapper: AlbumEntityToAlbumMapper
    private lateinit var albumDtoToAlbumEntityMapper: AlbumDtoToAlbumEntityMapper
    private lateinit var albumRepositoryImpl: AlbumRepositoryImpl

    @Before
    fun setup() {
        albumApiDataSource = mockk()
        albumLocalDataSource = mockk()
        albumEntityToAlbumMapper = mockk()
        albumDtoToAlbumEntityMapper = mockk()
        albumRepositoryImpl = AlbumRepositoryImpl(
            albumApiDataSource,
            albumLocalDataSource,
            albumEntityToAlbumMapper,
            albumDtoToAlbumEntityMapper
        )
    }

    @Test
    fun `getAlbums should return data from local data source when local data source returns datad`() {
        runBlocking {
            // Given
            val entityList = listOf(
                AlbumEntity(
                    albumId = 1,
                    id = 1,
                    title = "album 1",
                    url = "url1",
                    thumbnailUrl = "url1"
                ),
                AlbumEntity(
                    albumId = 2,
                    id = 2,
                    title = "title 2",
                    url = "url",
                    thumbnailUrl = "thumbnailUrl"
                )
            )
            val flow = flowOf(entityList)
            val expectedList = listOf(
                Album(
                    albumId = 1,
                    id = 1,
                    title = "title 1",
                    url = "url1",
                    thumbnailUrl = ""
                ),
                Album(
                    albumId = 2,
                    id = 2,
                    title = "title 2",
                    url = "url",
                    thumbnailUrl = "thumbnailUrl"
                )
            )
            every { albumLocalDataSource.getAlbums() } returns flow
            entityList.forEachIndexed { index, albumEntity ->
                every { albumEntityToAlbumMapper.mapFrom(albumEntity) } returns expectedList[index]
            }

            // When
            val albumsFlow = albumRepositoryImpl.getAlbums()

            // Then
            albumsFlow.collect { resource ->
                MatcherAssert.assertThat("Resource should be success", resource is Resource.Success)
                MatcherAssert.assertThat(
                    "Resource data should match expected list",
                    (resource as Resource.Success).data == expectedList
                )
            }
        }
    }

    @Test
    fun `getAlbums -  insert data from remote data source into local data source when local data source returns empty list`() {
        runBlocking {
            // Given
            val emptyEntityList = emptyList<AlbumEntity>()
            val remoteEntityList = listOf(
                AlbumEntity(
                    albumId = 1,
                    id = 1,
                    title = "title 1",
                    url = "url1",
                    thumbnailUrl = ""
                ),
                AlbumEntity(
                    albumId = 2,
                    id = 2,
                    title = "title 2",
                    url = "url2",
                    thumbnailUrl = "thumbnailUrl2"
                )
            )
            val remoteList = listOf(
                Album(
                    albumId = 1,
                    id = 1,
                    title = "title 1",
                    url = "url1",
                    thumbnailUrl = ""
                ),
                Album(
                    albumId = 2,
                    id = 2,
                    title = "title 2",
                    url = "url2",
                    thumbnailUrl = "thumbnailUrl2"
                )
            )
            val emptyFlow = flowOf(emptyEntityList)
            val remoteFlow = flowOf(remoteEntityList)
            every { albumLocalDataSource.getAlbums() } returns emptyFlow andThen remoteFlow
            remoteEntityList.forEachIndexed { index, albumEntity ->
                //every { albumDtoToAlbumEntityMapper.mapFrom(remoteList[index]) } returns albumEntity
            }
            every { albumLocalDataSource.insertAlbums(remoteEntityList) } just runs
            remoteEntityList.forEachIndexed { index, albumEntity ->
                every { albumEntityToAlbumMapper.mapFrom(albumEntity) } returns remoteList[index]
            }

            // When
            val albumsFlow = albumRepositoryImpl.getAlbums()

            // Then
            albumsFlow.collect { resource ->
                MatcherAssert.assertThat("Resource should be success", resource is Resource.Success)
                MatcherAssert.assertThat(
                    "Resource data should match expected list",
                    (resource as Resource.Success).data == remoteList
                )
            }
        }

    }

    @Test
    fun `getAlbums - should return error when remote data source throws exception`() {
        runBlocking {
            // Given
            val emptyEntityList = emptyList<AlbumEntity>()
            val emptyFlow = flowOf(emptyEntityList)
            val exception = Exception("Error")
            every { albumLocalDataSource.getAlbums() } returns emptyFlow
            coEvery { albumApiDataSource.getAlbums() } throws exception

            // When
            val albumsFlow = albumRepositoryImpl.getAlbums()

            // Then
            albumsFlow.collect { resource ->
                MatcherAssert.assertThat("Resource should be failure", resource is Resource.Failure)
                MatcherAssert.assertThat(
                    "Resource exception should match expected exception",
                    (resource as Resource.Failure).exception == exception
                )
            }
        }
    }
}