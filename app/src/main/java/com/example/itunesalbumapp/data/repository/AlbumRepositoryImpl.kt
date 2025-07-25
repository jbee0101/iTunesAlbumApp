package com.example.itunesalbumapp.data.repository

import com.example.itunesalbumapp.data.local.AlbumDatabase
import com.example.itunesalbumapp.data.mapper.toDomain
import com.example.itunesalbumapp.data.mapper.toEntity
import com.example.itunesalbumapp.data.remote.AlbumApiService
import com.example.itunesalbumapp.domain.model.Album
import com.example.itunesalbumapp.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: AlbumApiService,
    private val db: AlbumDatabase
) : AlbumRepository {
    override suspend fun getAlbums(): Flow<List<Album>> = flow {
        val local = db.albumDao().getAll().firstOrNull()
        if (local.isNullOrEmpty()) {
            val remote = api.getTopAlbums().feed.entries.map { it.toEntity() }
            db.albumDao().insertAll(remote)
        }
        emitAll(db.albumDao().getAll().map { it.map { entity -> entity.toDomain() } })
    }
}
