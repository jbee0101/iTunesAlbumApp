package com.example.itunesalbumapp.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.itunesalbumapp.domain.model.Album

@Composable
fun AlbumDetailScreen(
    album: Album
) {
    Column {
        Text(album.name)
        Text(album.artist)
        Text(album.price)
    }
}
