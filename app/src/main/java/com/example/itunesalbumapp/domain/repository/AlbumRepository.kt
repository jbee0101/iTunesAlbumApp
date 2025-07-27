package com.example.itunesalbumapp.domain.repository

import com.example.itunesalbumapp.domain.model.Album
import kotlinx.coroutines.flow.Flow

/**
 * Represents the repository for albums.
 */
interface AlbumRepository {
   /**
    * Fetches the albums from the remote API.
    *
    * @return A [Flow] emitting a list of [Album].
    */
   suspend fun getAlbums(): Flow<List<Album>>
}
