package com.example.itunesalbumapp.domain.model

sealed class UiState<out T> {

    data object Loading : UiState<Nothing>()

    data class Success<T>(val data: T) : UiState<T>()

    data class Empty(val message: String = "No data found") : UiState<Nothing>()

    data class Error(val message: String = "Something went wrong") : UiState<Nothing>()
}