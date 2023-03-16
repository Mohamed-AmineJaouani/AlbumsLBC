package com.example.albumsleboncoin.data.datasource

import com.example.albumsleboncoin.data.api.AlbumService
import javax.inject.Inject

class AlbumApiDataSource @Inject constructor(private val albumService: AlbumService) {

    suspend fun getAlbums() = try {
        albumService.getAlbums()
    } catch (e: Exception) {
        throw e
    }
}