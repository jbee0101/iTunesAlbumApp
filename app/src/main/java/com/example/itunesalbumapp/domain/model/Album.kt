package com.example.itunesalbumapp.domain.model

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