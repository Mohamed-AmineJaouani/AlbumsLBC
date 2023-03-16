package com.example.common.usecase

import com.example.common.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

abstract class FlowUseCase<in Params, out RESULT>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(params: Params? = null): Flow<Resource<RESULT>> = flow {
        withContext(coroutineDispatcher) {
            try {
                val result = execute(params)
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    abstract suspend fun execute(params: Params? = null): RESULT
}