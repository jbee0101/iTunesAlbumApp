package com.example.itunesalbumapp.domain.usecase

import com.example.itunesalbumapp.domain.model.Album
import com.example.itunesalbumapp.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(): Flow<List<Album>> = flow {
        val result = repository.getAlbums()
        result.collect { albums ->
            emit(albums)
        }
    }
}
