package com.example.itunesalbumapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey val id: String,
    val name: String,
    val artist: String,
    val imageUrl: String,
    val price: String,
    val releaseDate: String,
    val link: String
)
