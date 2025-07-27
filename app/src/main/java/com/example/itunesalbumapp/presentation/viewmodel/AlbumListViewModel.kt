package com.example.itunesalbumapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesalbumapp.domain.model.Album
import com.example.itunesalbumapp.domain.model.UiState
import com.example.itunesalbumapp.domain.usecase.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the album list screen.
 *
 * @param getAlbumsUseCase The use case for fetching albums.
 */
@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    private var _isRefreshing = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<UiState<List<Album>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Album>>> = _uiState


    init {
        fetchAlbums()
    }

    /**
     * Fetches albums from the use case and updates the UI state accordingly.
     */
    private fun fetchAlbums() {
        viewModelScope.launch {
            getAlbumsUseCase.invoke()
                .catch {
                    Log.e("AlbumListViewModel", "Error fetching albums", it)
                }.collect {
                    _uiState.value = it
                }
        }
    }

    /**
     * Refreshes the albums by fetching them again.
     */
    fun refreshAlbums() {
        if (_isRefreshing.value) return

        viewModelScope.launch {
            _isRefreshing.value = true
            fetchAlbums()
            _isRefreshing.value = false
        }
    }
}