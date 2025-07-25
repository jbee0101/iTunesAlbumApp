package com.example.itunesalbumapp.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.itunesalbumapp.domain.model.Album
import com.example.itunesalbumapp.presentation.viewmodel.AlbumListViewModel

@Composable
fun AlbumListScreen(
    viewModel: AlbumListViewModel,
    onAlbumClick: (Album) -> Unit
) {
    val albums by viewModel.albums.collectAsState()

    LazyColumn {
        items(albums) { album ->
            Text(album.name, Modifier.clickable { onAlbumClick(album) })
        }
    }
}