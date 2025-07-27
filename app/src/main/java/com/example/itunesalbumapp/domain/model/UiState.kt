package com.example.itunesalbumapp.domain.model

/**
 * Represents the state of the UI.
 *
 * @param T The type of data being displayed.
 */
sealed class UiState<out T> {

    /**
     * Represents the loading state.
     *
     * @constructor Creates an instance of [Loading].
     */
    data object Loading : UiState<Nothing>()

    /**
     * Represents the success state with data.
     *
     * @param data The data being displayed.
     * @constructor Creates an instance of [Success].
     * @param T The type of data being displayed.
     */
    data class Success<T>(val data: T) : UiState<T>()

    /**
     * Represents the empty state.
     *
     * @param message The message to be displayed.
     * @constructor Creates an instance of [Empty].
     */
    data class Empty(val message: String = "No data found") : UiState<Nothing>()

    /**
     * Represents the error state.
     *
     * @param message The message to be displayed.
     * @constructor Creates an instance of [Error].
     */
    data class Error(val message: String = "Something went wrong") : UiState<Nothing>()
}