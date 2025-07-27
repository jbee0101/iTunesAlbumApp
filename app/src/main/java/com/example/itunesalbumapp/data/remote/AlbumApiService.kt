package com.example.itunesalbumapp.data.remote

import retrofit2.http.GET

/**
 * Represents the API service for retrieving top albums.
 */
interface AlbumApiService {
    /**
     * Retrieves the top albums from the iTunes API.
     */
    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): AlbumFeedDto
}
