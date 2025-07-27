package com.example.itunesalbumapp.domain.usecase

import com.example.itunesalbumapp.domain.model.Album
import com.example.itunesalbumapp.domain.model.UiState
import com.example.itunesalbumapp.domain.repository.AlbumRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Test class for [GetAlbumsUseCase].
 */
class GetAlbumsUseCaseTest {

    private lateinit var useCase: GetAlbumsUseCase
    private val repository: AlbumRepository = mockk()

    /**
     * Sets up the test environment.
     */
    @Before
    fun setUp() {
        useCase = GetAlbumsUseCase(repository)
    }

    /**
     * Tests the [GetAlbumsUseCase.invoke] function.
     */
    @Test
    fun `should emit Loading then Success when repository returns albums`() = runTest {
        val albums = listOf(Album("1", "Album1", "Artist", "url", "$10", "2023-01-01", "copyright", "genre", "link"))
        coEvery { repository.getAlbums() } returns flowOf(albums)

        val emissions = useCase().toList()

        assertEquals(UiState.Loading, emissions[0])
        assert(emissions[1] is UiState.Success)
        val successState = emissions[1] as UiState.Success
        assertEquals(albums, successState.data)
    }

    /**
     * Tests the [GetAlbumsUseCase.invoke] function.
     */
    @Test
    fun `should emit Loading then Empty when repository returns empty list`() = runTest {
        coEvery { repository.getAlbums() } returns flowOf(emptyList())

        val emissions = useCase().toList()

        assertEquals(UiState.Loading, emissions[0])
        assert(emissions[1] is UiState.Empty)
    }

    /**
     * Tests the [GetAlbumsUseCase.invoke] function.
     */
    @Test
    fun `should emit Loading then Error when repository throws exception`() = runTest {
        coEvery { repository.getAlbums() } returns flow {
            throw RuntimeException("Network error")
        }

        val emissions = useCase().toList()

        assertEquals(UiState.Loading, emissions[0])
        assert(emissions[1] is UiState.Error)
        val errorState = emissions[1] as UiState.Error
        assertEquals("Network error", errorState.message)
    }
}