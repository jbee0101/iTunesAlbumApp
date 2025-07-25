package com.example.itunesalbumapp.data.remote

import retrofit2.http.GET

interface AlbumApiService {
    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): AlbumFeedDto
}
