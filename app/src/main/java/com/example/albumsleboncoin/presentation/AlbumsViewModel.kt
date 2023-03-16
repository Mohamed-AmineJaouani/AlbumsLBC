package com.example.albumsleboncoin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumsleboncoin.domain.GetAlbum
import com.example.common.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val album: GetAlbum,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        getAlbum()
    }

    //general flow album ui state variable public an private
    private val _albumUiState = MutableStateFlow<AlbumUiState>(AlbumUiState.Idl)
    val albumUiState: StateFlow<AlbumUiState> = _albumUiState

    fun getAlbum() {
        viewModelScope.launch {
            album.execute().collect { resource ->
                when (resource) {
                    is Resource.Success -> _albumUiState.value = AlbumUiState.Success(resource.data)
                    is Resource.Failure -> _albumUiState.value = AlbumUiState.Error(1)
                    else -> {}
                }
            }
        }
    }

    fun setState(albumUiState: AlbumUiState) {
        _albumUiState.value = albumUiState
    }

}