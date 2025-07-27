package com.example.itunesalbumapp.domain.usecase

import com.example.itunesalbumapp.domain.model.Album
import com.example.itunesalbumapp.domain.model.UiState
import com.example.itunesalbumapp.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for getting albums.
 *
 * @property repository The repository for albums.
 */
class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    /**
     * Invokes the use case to get albums.
     *
     * @return A [Flow] emitting a [UiState] with a list of [Album].
     */
    operator fun invoke(): Flow<UiState<List<Album>>> = flow {
        emit(UiState.Loading)
        val result = repository.getAlbums()
        result.collect { albums ->
            if (albums.isEmpty()) {
                emit(UiState.Empty())
            } else {
                emit(UiState.Success(albums))
            }
        }
    }.catch { e ->
        emit(UiState.Error(e.localizedMessage ?: "An unexpected error occurred"))
    }
}
