package com.example.albumsleboncoin.data.api

import com.example.albumsleboncoin.BuildConfig
import com.example.common.ApiServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAlbumService(): AlbumService {
        return ApiServiceFactory.makeService(AlbumService::class.java, BuildConfig.DEBUG)
    }
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}