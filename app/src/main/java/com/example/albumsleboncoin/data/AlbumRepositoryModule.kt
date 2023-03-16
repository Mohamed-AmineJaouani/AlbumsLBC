package com.example.albumsleboncoin.data

import com.example.albumsleboncoin.data.api.AlbumService
import com.example.albumsleboncoin.data.datasource.AlbumApiDataSource
import com.example.albumsleboncoin.data.datasource.AlbumLocalDataSource
import com.example.albumsleboncoin.data.local.database.AlbumDao
import com.example.albumsleboncoin.domain.AlbumRepository
import com.example.albumsleboncoin.mapper.AlbumDtoToAlbumEntityMapper
import com.example.albumsleboncoin.mapper.AlbumEntityToAlbumMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {
    @Provides
    fun provideAlbumApiDataSource(albumService: AlbumService): AlbumApiDataSource = AlbumApiDataSource(albumService)

    @Provides
    fun provideAlbumLocalDataSource(albumDao: AlbumDao): AlbumLocalDataSource = AlbumLocalDataSource(albumDao)

    @Provides
    fun provideAlbumEntityToAlbumMapper(): AlbumEntityToAlbumMapper = AlbumEntityToAlbumMapper()

    @Provides
    fun provideAlbumDtoToAlbumEntityMapper(): AlbumDtoToAlbumEntityMapper = AlbumDtoToAlbumEntityMapper()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AlbumRepositoryModule {
    @Binds
    abstract fun bindAlbumRepository(repository: AlbumRepositoryImpl): AlbumRepository
}