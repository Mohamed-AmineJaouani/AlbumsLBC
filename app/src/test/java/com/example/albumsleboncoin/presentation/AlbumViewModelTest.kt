package com.example.albumsleboncoin.presentation

import com.example.albumsleboncoin.domain.Album
import com.example.albumsleboncoin.domain.GetAlbum
import com.example.common.resource.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class AlbumViewModelTest {

    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var getAlbum: GetAlbum
    private lateinit var viewModel: AlbumViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        dispatcher = Dispatchers.Unconfined
        Dispatchers.setMain(dispatcher)
        getAlbum = mockk()
        viewModel = AlbumViewModel(getAlbum, dispatcher)
    }

    @Test
    fun `getAlbum - should update albumUiState with success when getAlbums returns success`() {
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
            val successResource = Resource.Success(albumList)
            coEvery { getAlbum.execute() } returns flowOf(successResource)

            // When
            viewModel.getAlbum()

            // Then
            viewModel.albumUiState.collect { uiState ->
                MatcherAssert.assertThat("AlbumUiState should be Success", uiState is AlbumUiState.Success)
                MatcherAssert.assertThat(
                    "AlbumUiState data should match expected list",
                    (uiState as AlbumUiState.Success).albums == albumList
                )
            }
        }
    }

    @Test
    fun `getAlbum - should update albumUiState with error when getAlbums returns failure`() {
        runBlocking {
            // Given
            val exception = Exception("An error occurred")
            val failureResource = Resource.Failure(exception)
            coEvery { getAlbum.execute() } returns flowOf(failureResource)

            // When
            viewModel.getAlbum()

            // Then
            viewModel.albumUiState.collect { uiState ->
                MatcherAssert.assertThat("AlbumUiState should be Error", uiState is AlbumUiState.Error)
            }
        }
    }

    @Test
    fun `getAlbum - should update albumUiState with empty list when getAlbums returns empty data`() {
        runBlocking {
            // Given
            val emptyResource = Resource.Success(emptyList<Album>())
            coEvery { getAlbum.execute() } returns flowOf(emptyResource)

            // When
            viewModel.getAlbum()

            // Then
            viewModel.albumUiState.collect { uiState ->
                MatcherAssert.assertThat("AlbumUiState should be Success", uiState is AlbumUiState.Success)
                MatcherAssert.assertThat(
                    "AlbumUiState data should be empty list",
                    (uiState as AlbumUiState.Success).albums.isEmpty()
                )
            }
        }
    }
}