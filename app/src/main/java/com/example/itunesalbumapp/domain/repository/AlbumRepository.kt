package com.example.itunesalbumapp.domain.repository

import com.example.itunesalbumapp.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
   suspend fun getAlbums(): Flow<List<Album>>
}
