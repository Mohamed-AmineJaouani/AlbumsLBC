package com.example.albumsleboncoin.domain

import com.example.common.resource.Resource
import com.example.common.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAlbum @Inject constructor(
    private val albumRepository: AlbumRepository,
    coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit?, Flow<Resource<List<Album>>>>(coroutineDispatcher) {

    override suspend fun execute(params: Unit?): Flow<Resource<List<Album>>> {
        return albumRepository.getAlbums()
            .catch { e -> emit(Resource.Failure(e)) }
            .flowOn(Dispatchers.IO)
    }
}