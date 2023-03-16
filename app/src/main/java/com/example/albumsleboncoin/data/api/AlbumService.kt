package com.example.albumsleboncoin.data.api

import retrofit2.http.GET

interface AlbumService {
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumDto>
}