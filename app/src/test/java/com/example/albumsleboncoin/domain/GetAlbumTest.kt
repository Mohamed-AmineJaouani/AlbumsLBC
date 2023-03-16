package com.example.albumsleboncoin.domain

import com.example.common.resource.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class GetAlbumTest {

    private lateinit var albumRepository: AlbumRepository
    private lateinit var coroutineDispatcher: CoroutineDispatcher
    private lateinit var getAlbum: GetAlbum

    @Before
    fun setUp() {
        albumRepository = mockk()
        coroutineDispatcher = mockk()
        getAlbum = GetAlbum(albumRepository, coroutineDispatcher)
    }

    @Test
    fun `execute should return data when albumRepository returns Success`() {
        runBlocking {
            // Given
            val albumList = listOf(
                Album(
                    albumId = 1,
                    id = 1,
                    title = "album 1",
                    url = "url1",
                    thumbnailUrl = "url1"
                ),
                Album(
                    albumId = 2,
                    id = 2,
                    title = "album 2",
                    url = "url2",
                    thumbnailUrl = "url2"
                )
            )
            coEvery { albumRepository.getAlbums() } returns flowOf(Resource.Success(albumList))

            // When
            val result = getAlbum.execute(null).first()

            // Then
            MatcherAssert.assertThat("Result should be success", result is Resource.Success)
            MatcherAssert.assertThat(
                "Result data should match expected list",
                (result as Resource.Success).data == albumList
            )
        }
    }

    @Test
    fun `execute should return Failure when albumRepository returns Failure`() {
        runBlocking {
            // Given
            val exception = Exception("An error occurred")
            val params: Nothing? = null
            val expected = Resource.Failure(exception)
            coEvery { albumRepository.getAlbums() } returns flow { emit(expected) }

            // When
            val actual = getAlbum.execute(params).single()

            // Then
            MatcherAssert.assertThat("Result should be Failure", actual is Resource.Failure)
            MatcherAssert.assertThat(
                "Error should match expected exception",
                (actual as Resource.Failure).exception == exception
            )
        }
    }

    @Test
    fun `execute should return empty list when albumRepository returns empty data`() {
        runBlocking {
            // Given
            val emptyResource = Resource.Success(emptyList<Album>())
            coEvery { albumRepository.getAlbums() } returns flowOf(emptyResource)

            // When
            val albums = getAlbum.execute(null).first()

            // Then
            MatcherAssert.assertThat("Resource should be success", albums is Resource.Success)
            MatcherAssert.assertThat(
                "Resource data should be empty list",
                (albums as Resource.Success).data.isEmpty()
            )
        }
    }
}
