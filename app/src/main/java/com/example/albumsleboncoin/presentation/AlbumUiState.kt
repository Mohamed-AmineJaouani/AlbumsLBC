package com.example.albumsleboncoin.presentation

import com.example.albumsleboncoin.domain.Album

sealed class AlbumUiState {
    object Idl : AlbumUiState()
    object Loading : AlbumUiState()
    data class Success(val albums: List<Album>) : AlbumUiState()
    data class Error(val error: Int) : AlbumUiState()
}