package com.example.itunesalbumapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.itunesalbumapp.domain.model.Album
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.net.URLEncoder

/**
 * Test class for [AlbumDetailViewModel].
 */
class AlbumDetailViewModelTest {

    /**
     * Sets up the test environment.
     */
    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
    }

    /**
     * Tests the [AlbumDetailViewModel.album] property.
     */
    @Test
    fun `album should be parsed from SavedStateHandle when valid JSON`() = runTest {

        val album = Album(
            id = "1",
            name = "Test Album",
            artist = "Test Artist",
            imageUrl = "http://image.com",
            price = "$10",
            releaseDate = "2023-01-01",
            copyright = "Test Copyright",
            genre = "Test Genre",
            link = "http://link.com"
        )
        val json = Gson().toJson(album)
        val encodedJson = URLEncoder.encode(json, "utf-8")

        val savedStateHandle = SavedStateHandle(mapOf("album" to encodedJson))

        val viewModel = AlbumDetailViewModel(savedStateHandle)
        val result = viewModel.album.first()

        assertNotNull(result)
        assertEquals(album.id, result?.id)
        assertEquals(album.name, result?.name)
    }

    /**
     * Tests the [AlbumDetailViewModel.album] property.
     */
    @Test
    fun `album should be null when SavedStateHandle does not contain album`() = runTest {

        val savedStateHandle = SavedStateHandle()

        val viewModel = AlbumDetailViewModel(savedStateHandle)
        val result = viewModel.album.first()

        assertNull(result)
    }

    /**
     * Tests the [AlbumDetailViewModel.album] property.
     */
    @Test
    fun `album should be null when JSON is invalid`() = runTest {

        val invalidJson = URLEncoder.encode("{invalid_json}", "utf-8")
        val savedStateHandle = SavedStateHandle(mapOf("album" to invalidJson))

        val viewModel = AlbumDetailViewModel(savedStateHandle)
        val result = viewModel.album.first()

        assertNull(result)
    }
}
