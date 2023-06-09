package com.example.albumsleboncoin.mapper.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {

    fun randomString(): String{
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int{
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun stringValue(): String{
        return "AAAA-BBBB_CCCC"
    }
}