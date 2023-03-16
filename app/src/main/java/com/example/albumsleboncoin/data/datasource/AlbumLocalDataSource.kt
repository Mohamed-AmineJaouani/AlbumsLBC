package com.example.albumsleboncoin.data.datasource

import com.example.albumsleboncoin.data.local.database.AlbumDao
import com.example.albumsleboncoin.data.local.database.AlbumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class AlbumLocalDataSource @Inject constructor(private val albumDao: AlbumDao) {

    fun getAlbums(): Flow<List<AlbumEntity>> {
        return try {
            albumDao.getAlbums()
        } catch (e: Exception) {
            throw e
        }
    }

    fun insertAlbums(albums: List<AlbumEntity>) = albumDao.insertAlbums(albums)
}
