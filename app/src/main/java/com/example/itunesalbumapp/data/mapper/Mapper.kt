package com.example.itunesalbumapp.data.mapper

import com.example.itunesalbumapp.data.local.AlbumEntity
import com.example.itunesalbumapp.data.remote.EntryDto
import com.example.itunesalbumapp.domain.model.Album

fun EntryDto.toEntity(): AlbumEntity = AlbumEntity(
    id = id.attributes.id,
    name = name.label,
    artist = artist.label,
    imageUrl = images.firstOrNull()?.url ?: "",
    price = price?.label ?: "",
    releaseDate = releaseDate.date,
    link = link.attributes.href
)

fun AlbumEntity.toDomain(): Album = Album(
    id = id,
    name = name,
    artist = artist,
    imageUrl = imageUrl,
    price = price,
    releaseDate = releaseDate,
    link = link
)