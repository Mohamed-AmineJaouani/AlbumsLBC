package com.example.albumsleboncoin.mapper

import com.example.albumsleboncoin.mapper.factory.AlbumFactory
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AlbumEntityToAlbumMapperTest{
    private val mapper = AlbumEntityToAlbumMapper()

    @Test
    fun mapFromEntityMapsData() {
        val albumEntity = AlbumFactory.makeAlbumsEntity()
        val album = mapper.mapFrom(albumEntity)

        assertEquals(albumEntity.albumId, album.albumId)
        assertEquals(albumEntity.id, album.id)
        assertEquals(albumEntity.title, album.title)
        assertEquals(albumEntity.url, album.url)
        assertEquals(albumEntity.thumbnailUrl, album.thumbnailUrl)
    }

}