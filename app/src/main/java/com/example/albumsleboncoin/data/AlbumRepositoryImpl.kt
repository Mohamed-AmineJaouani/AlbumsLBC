package com.example.albumsleboncoin.data

import com.example.albumsleboncoin.data.api.AlbumDto
import com.example.albumsleboncoin.data.datasource.AlbumApiDataSource
import com.example.albumsleboncoin.data.datasource.AlbumLocalDataSource
import com.example.albumsleboncoin.data.local.database.AlbumEntity
import com.example.albumsleboncoin.domain.Album
import com.example.albumsleboncoin.domain.AlbumRepository
import com.example.albumsleboncoin.mapper.AlbumDtoToAlbumEntityMapper
import com.example.albumsleboncoin.mapper.AlbumEntityToAlbumMapper
import com.example.common.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumApiDataSource: AlbumApiDataSource,
    private val albumLocalDataSource: AlbumLocalDataSource,
    private val albumEntityToAlbumMapper: AlbumEntityToAlbumMapper,
    private val albumDtoToAlbumEntityMapper: AlbumDtoToAlbumEntityMapper,
) : AlbumRepository {

    override suspend fun getAlbums(): Flow<Resource<List<Album>>> = flow {
        albumLocalDataSource.getAlbums().first()
            .takeIf { it.isEmpty() }
            ?.let {
                try {
                    albumApiDataSource.getAlbums()
                        .takeIf {
                            it.isNotEmpty()
                        }
                        ?.let { albumLocalDataSource.insertAlbums(it.toAlbumEntities()) }
                } catch (e: Exception) {
                    emit(Resource.Failure(e))
                }
            }
        emit(Resource.Success(albumLocalDataSource.getAlbums().first().toAlbums()))
    }

    private fun List<AlbumEntity>.toAlbums() = map {
        albumEntityToAlbumMapper.mapFrom(it)
    }

    private fun List<AlbumDto>.toAlbumEntities() = map {
        albumDtoToAlbumEntityMapper.mapFrom(it)
    }
}