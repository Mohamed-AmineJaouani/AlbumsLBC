package com.example.albumsleboncoin.mapper

import com.example.albumsleboncoin.data.local.database.AlbumEntity
import com.example.albumsleboncoin.domain.Album
import com.example.common.mapper.Mapper
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
class AlbumEntityToAlbumMapper : Mapper<AlbumEntity, Album> {
    override fun mapFrom(from: AlbumEntity): Album {
        return Album(
            id = from.id,
            albumId = from.albumId,
            title = from.title,
            url = from.url,
            thumbnailUrl = from.thumbnailUrl,
        )
    }
}
