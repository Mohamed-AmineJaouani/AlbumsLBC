package com.example.albumsleboncoin.mapper

import com.example.albumsleboncoin.mapper.factory.AlbumFactory
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AlbumDtoToAlbumEntityMapperTest{
    private val mapper = AlbumDtoToAlbumEntityMapper()

    @Test
    fun mapFromEntityMapsData() {
        val albumDto = AlbumFactory.makeAlbumDto()
        val albumEntity = mapper.mapFrom(albumDto)

        assertEquals(albumDto.albumId, albumEntity.albumId)
        assertEquals(albumDto.id, albumEntity.id)
        assertEquals(albumDto.title, albumEntity.title)
        assertEquals(albumDto.url, albumEntity.url)
        assertEquals(albumDto.thumbnailUrl, albumEntity.thumbnailUrl)
    }

}