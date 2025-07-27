package com.example.itunesalbumapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.itunesalbumapp.domain.model.Album
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.URLDecoder
import javax.inject.Inject

/**
 * ViewModel for the album detail screen.
 *
 * @param savedStateHandle The saved state handle for this ViewModel.
 */
@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _album = MutableStateFlow<Album?>(null)
    val album: StateFlow<Album?> = _album

    /**
     * Initializes the ViewModel with the album data from the saved state handle.
     */
    init {
        savedStateHandle.get<String>("album")?.let { encodedJson ->
            try {
                val json = URLDecoder.decode(encodedJson, "utf-8")
                val albumObj = Gson().fromJson(json, Album::class.java)
                _album.value = albumObj
            } catch (e: Exception) {
                Log.e("AlbumDetailVM", "Error decoding album: ${e.message}")
            }
        }
    }
}