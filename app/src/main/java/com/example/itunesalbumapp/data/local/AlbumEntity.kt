package com.example.itunesalbumapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing an album in the database.
 *
 * @property id The unique identifier of the album.
 * @property name The name of the album.
 * @property artist The name of the artist associated with the album.
 * @property imageUrl The URL of the album's image.
 * @property price The price of the album.
 * @property releaseDate The release date of the album.
 * @property copyright The copyright information of the album.
 * @property genre The genre of the album.
 * @property link The iTunes link associated with the album.
 */
@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey val id: String,
    val name: String,
    val artist: String,
    val imageUrl: String,
    val price: String,
    val releaseDate: String,
    val copyright: String,
    val genre: String,
    val link: String
)
