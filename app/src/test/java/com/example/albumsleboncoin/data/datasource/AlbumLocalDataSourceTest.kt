package com.example.albumsleboncoin.data.datasource

import com.example.albumsleboncoin.data.local.database.AlbumDao
import com.example.albumsleboncoin.data.local.database.AlbumEntity
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.*


class AlbumLocalDataSourceTest {

    private lateinit var albumDao: AlbumDao
    private lateinit var albumLocalDataSource: AlbumLocalDataSource

    @Before
    fun setup() {
        albumDao = mock(AlbumDao::class.java)
        albumLocalDataSource = AlbumLocalDataSource(albumDao)
    }

    @Test
    fun `getAlbums should return data when AlbumDao returns data`() {
        runBlocking {
            // Given
            val albumEntityList = listOf(
                AlbumEntity(
                    albumId = 1,
                    id = 1,
                    title = "album 1",
                    url = "url1",
                    thumbnailUrl = "url1",
                ),
                AlbumEntity(
                    albumId = 2,
                    id = 2,
                    title = "album 2",
                    url = "url2",
                    thumbnailUrl = "url2"
                )
            )
            val flow = flowOf(albumEntityList)
            `when`(albumDao.getAlbums()).thenReturn(flow)

            // When
            val albumsFlow = albumLocalDataSource.getAlbums()

            // Then
            albumsFlow.collect { albums ->
                assertThat(
                    "Albums should match data returned by AlbumDao",
                    albums == albumEntityList
                )
            }
        }
    }

    @Test
    fun `insertAlbums should call insertAlbums on AlbumDao`() {
        runBlocking {
            // Given
            val albumEntityList = listOf(
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
                    title = "album 2",
                    url = "url2",
                    thumbnailUrl = "thumbnailUrl2"
                )
            )

            // When
            albumLocalDataSource.insertAlbums(albumEntityList)

            // Then
            verify(albumDao).insertAlbums(albumEntityList)
        }
    }
}