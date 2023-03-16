package com.example.common.resource

sealed class Resource<out RESULT> {
    data class Success<out RESULT>(val data: RESULT) : Resource<RESULT>()
    data class Failure(val exception: Throwable) : Resource<Nothing>()
}
