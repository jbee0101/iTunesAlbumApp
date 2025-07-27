package com.example.itunesalbumapp.data.mapper

import com.example.itunesalbumapp.data.local.AlbumEntity
import com.example.itunesalbumapp.data.remote.EntryDto
import com.example.itunesalbumapp.domain.model.Album

/**
 * Converts an [EntryDto] to an [AlbumEntity].
 *
 * @return The converted [AlbumEntity].
 */
fun EntryDto.toEntity(): AlbumEntity = AlbumEntity(
    id = id.attributes.id,
    name = name.label,
    artist = artist.label,
    imageUrl = images.firstOrNull()?.url ?: "",
    price = price?.label ?: "",
    copyright = rights?.label ?: "",
    genre = category.attributes.label,
    releaseDate = releaseDate.date,
    link = link.attributes.href
)

/**
 * Converts an [AlbumEntity] to a [Album].
 *
 * @return The converted [Album].
 */
fun AlbumEntity.toDomain(): Album = Album(
    id = id,
    name = name,
    artist = artist,
    imageUrl = imageUrl,
    price = price,
    releaseDate = releaseDate,
    copyright = copyright,
    genre = genre,
    link = link
)