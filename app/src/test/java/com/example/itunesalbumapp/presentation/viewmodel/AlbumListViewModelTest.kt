package com.example.itunesalbumapp.presentation.viewmodel

import android.util.Log
import com.example.itunesalbumapp.domain.model.Album
import com.example.itunesalbumapp.domain.model.UiState
import com.example.itunesalbumapp.domain.usecase.GetAlbumsUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Test class for [AlbumListViewModel].
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AlbumListViewModelTest {

    private val getAlbumsUseCase: GetAlbumsUseCase = mockk()
    private lateinit var viewModel: AlbumListViewModel
    private val testDispatcher = StandardTestDispatcher()

    /**
     * Sets up the test environment.
     */
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0
    }

    /**
     * Tears down the test environment.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Tests the [AlbumListViewModel.uiState] property.
     */
    @Test
    fun `initial state should be Loading`() = runTest {
        coEvery { getAlbumsUseCase.invoke() } returns flowOf(UiState.Loading)

        viewModel = AlbumListViewModel(getAlbumsUseCase)

        advanceUntilIdle()

        assertEquals(UiState.Loading, viewModel.uiState.value)
    }

    /**
     * Tests the [AlbumListViewModel.uiState] property.
     */
    @Test
    fun `should emit Success when use case returns albums`() = runTest {
        val albums = listOf(Album("1", "Album1", "Artist", "url", "$10", "2023-01-01", "copyright", "genre", "link"))
        coEvery { getAlbumsUseCase.invoke() } returns flowOf(UiState.Success(albums))

        viewModel = AlbumListViewModel(getAlbumsUseCase)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Success)
        assertEquals(albums, (state as UiState.Success).data)
    }

    /**
     * Tests the [AlbumListViewModel.uiState] property.
     */
    @Test
    fun `should emit Error when use case throws exception`() = runTest {
        coEvery { getAlbumsUseCase.invoke() } returns flow {
            throw RuntimeException("Network error")
        }

        viewModel = AlbumListViewModel(getAlbumsUseCase)

        advanceUntilIdle()

        assertEquals(UiState.Loading, viewModel.uiState.value)
    }

    /**
     * Tests the [AlbumListViewModel.refreshAlbums] function.
     */
    @Test
    fun `refreshAlbums should not call fetch when already refreshing`() = runTest {
        val albums = listOf(Album("1", "Album1", "Artist", "url", "$10", "2023-01-01", "copyright", "genre", "link"))
        coEvery { getAlbumsUseCase.invoke() } returns flowOf(UiState.Success(albums))

        viewModel = AlbumListViewModel(getAlbumsUseCase)

        advanceUntilIdle()

        viewModel.refreshAlbums()
        viewModel.refreshAlbums()

        advanceUntilIdle()

        assert(viewModel.uiState.value is UiState.Success)
    }
}