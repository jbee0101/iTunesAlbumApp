package com.example.itunesalbumapp.presentation.ui.navigation

import com.example.itunesalbumapp.domain.model.Album

sealed class Screen(val route: String) {
    object AlbumList : Screen("album_list")
    object AlbumDetail : Screen("album_detail/{album}") {
        fun createRoute(album: String) = "album_detail/$album"
    }
}