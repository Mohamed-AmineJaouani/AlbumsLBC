package com.example.albumsleboncoin.data.datasource

import com.example.albumsleboncoin.data.api.AlbumDto
import com.example.albumsleboncoin.data.api.AlbumService
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class AlbumApiDataSourceTest {

    private lateinit var albumService: AlbumService
    private lateinit var albumApiDataSource: AlbumApiDataSource

    @Before
    fun setup() {
        albumService = mock(AlbumService::class.java)
        albumApiDataSource = AlbumApiDataSource(albumService)
    }

    @Test
    fun `getAlbums should return data when AlbumService returns data`() {
        runBlocking {
            // Given
            val albumDtoList = listOf(
                AlbumDto(
                    albumId = 1,
                    id = 1,
                    title = "album 1",
                    url = "url1",
                    thumbnailUrl = "url1"
                ),
                AlbumDto(
                    albumId = 2,
                    id = 2,
                    title = "album 2",
                    url = "url2",
                    thumbnailUrl = "url2"
                )
            )
            `when`(albumService.getAlbums()).thenReturn(albumDtoList)

            // When
            val albums = runBlocking { albumApiDataSource.getAlbums() }

            // Then
            assertThat("", albums == albumDtoList)
        }
    }

    @Test(expected = Exception::class)
    fun `getAlbums should throw exception when AlbumService throws exception`() {
        runBlocking {
            // Given
            `when`(albumService.getAlbums()).thenThrow(Exception())

            // When
            val albums = runBlocking { albumApiDataSource.getAlbums() }

            // Then
            assertThat("", albums.isEmpty())
        }
    }
}