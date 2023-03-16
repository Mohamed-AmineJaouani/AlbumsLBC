package com.example.albumsleboncoin.mapper

import com.example.albumsleboncoin.data.api.AlbumDto
import com.example.albumsleboncoin.data.local.database.AlbumEntity
import com.example.common.mapper.Mapper

class AlbumDtoToAlbumEntityMapper : Mapper<AlbumDto, AlbumEntity> {
    override fun mapFrom(from: AlbumDto): AlbumEntity {
        return AlbumEntity(
            albumId = from.albumId,
            id = from.id,
            title = from.title,
            url = from.url,
            thumbnailUrl = from.thumbnailUrl,
        )
    }
}