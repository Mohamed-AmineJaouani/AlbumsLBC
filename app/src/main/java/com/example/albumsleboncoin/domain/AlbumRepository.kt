package com.example.albumsleboncoin.domain

import com.example.common.resource.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums(): Flow<Resource<List<Album>>>
}