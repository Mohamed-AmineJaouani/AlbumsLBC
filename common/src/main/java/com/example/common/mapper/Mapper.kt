package com.example.common.mapper

interface Mapper<FROM, TO> {
    fun mapFrom(from: FROM): TO
}