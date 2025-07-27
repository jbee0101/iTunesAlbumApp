package com.example.itunesalbumapp.domain.model

/**
 * Represents an album.
 *
 * @property id The ID of the album.
 * @property name The name of the album.
 * @property artist The name of the artist.
 * @property imageUrl The URL of the album's image.
 * @property price The price of the album.
 * @property releaseDate The release date of the album.
 * @property copyright The copyright information of the album.
 * @property genre The genre of the album.
 * @property link The link to the album's details.
 */
data class Album(
    val id: String,
    val name: String,
    val artist: String,
    val imageUrl: String,
    val price: String,
    val releaseDate: String,
    val copyright: String,
    val genre: String,
    val link: String
)