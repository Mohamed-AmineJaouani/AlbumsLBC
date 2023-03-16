package com.example.albumsleboncoin.mapper.factory

import com.example.albumsleboncoin.data.api.AlbumDto
import com.example.albumsleboncoin.data.local.database.AlbumEntity

object AlbumFactory {
    fun makeAlbumDto(): AlbumDto {
        return AlbumDto(
            albumId = DataFactory.randomInt(),
            id = DataFactory.randomInt(),
            title = DataFactory.stringValue(),
            url = DataFactory.stringValue(),
            thumbnailUrl = DataFactory.stringValue()
        )
    }

    fun makeAlbumsEntity(): AlbumEntity {
        return AlbumEntity(
            albumId = DataFactory.randomInt(),
            id = DataFactory.randomInt(),
            title = DataFactory.stringValue(),
            url = DataFactory.stringValue(),
            thumbnailUrl = DataFactory.stringValue()
        )
    }
}