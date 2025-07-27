package com.example.itunesalbumapp.data.repository

import com.example.itunesalbumapp.data.local.AlbumDao
import com.example.itunesalbumapp.data.local.AlbumDatabase
import com.example.itunesalbumapp.data.local.AlbumEntity
import com.example.itunesalbumapp.data.remote.AlbumApiService
import com.example.itunesalbumapp.data.remote.AlbumFeedDto
import com.example.itunesalbumapp.data.remote.ArtistDto
import com.example.itunesalbumapp.data.remote.AuthorDto
import com.example.itunesalbumapp.data.remote.CategoryAttributesDto
import com.example.itunesalbumapp.data.remote.CategoryDto
import com.example.itunesalbumapp.data.remote.ContentTypeDto
import com.example.itunesalbumapp.data.remote.EntryDto
import com.example.itunesalbumapp.data.remote.FeedDto
import com.example.itunesalbumapp.data.remote.IdAttributesDto
import com.example.itunesalbumapp.data.remote.IdDto
import com.example.itunesalbumapp.data.remote.LabelDto
import com.example.itunesalbumapp.data.remote.LabelTermDto
import com.example.itunesalbumapp.data.remote.LinkAttributesDto
import com.example.itunesalbumapp.data.remote.LinkDto
import com.example.itunesalbumapp.data.remote.PriceAttributesDto
import com.example.itunesalbumapp.data.remote.PriceDto
import com.example.itunesalbumapp.data.remote.ReleaseDateDto
import com.example.itunesalbumapp.data.remote.TypeLabelDto
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test class for [AlbumRepositoryImpl].
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AlbumRepositoryImplTest {

    private lateinit var repository: AlbumRepositoryImpl
    private val api: AlbumApiService = mockk()
    private val db: AlbumDatabase = mockk()
    private val albumDao: AlbumDao = mockk()

    private val testDispatcher = StandardTestDispatcher()

    /**
     * Sets up the test environment.
     */
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { db.albumDao() } returns albumDao
        repository = AlbumRepositoryImpl(api, db)
    }

    /**
     * Tears down the test environment.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Tests the [AlbumRepositoryImpl.getAlbums] function.
     */
    @Test
    fun `when local DB is empty, fetch from API and insert into DB`() = runTest {

        coEvery { albumDao.getAll() } returns flowOf(emptyList()) andThen  flowOf(
            listOf(sampleEntity())
        )
        coEvery { albumDao.insertAll(any()) } just Runs
        coEvery { api.getTopAlbums() } returns sampleApiResponse()

        val result = repository.getAlbums().first()

        coVerify(exactly = 1) { api.getTopAlbums() }
        coVerify(exactly = 1) { albumDao.insertAll(any()) }
        assert(result.isNotEmpty())
        assert(result.first().name == "Test Album")
    }

    /**
     * Tests the [AlbumRepositoryImpl.getAlbums] function.
     */
    @Test
    fun `when local DB has data, do not call API`() = runTest {

        coEvery { albumDao.getAll() } returns flowOf(listOf(sampleEntity()))
        coEvery { api.getTopAlbums() } returns sampleApiResponse()

        val result = repository.getAlbums().first()

        coVerify(exactly = 0) { api.getTopAlbums() } // API should not be called
        assert(result.first().name == "Test Album")
    }

    /**
     * Creates a sample [AlbumEntity].
     */
    private fun sampleEntity() = AlbumEntity(
        id = "1",
        name = "Test Album",
        artist = "Test Artist",
        imageUrl = "http://test.com/image.jpg",
        price = "$10",
        copyright = "Test Copyright",
        genre = "Test Type",
        releaseDate = "2023-01-01",
        link = "http://test.com"
    )

    /**
     * Creates a sample [AlbumFeedDto].
     */
    private fun sampleApiResponse(): AlbumFeedDto {
        return AlbumFeedDto(
            feed = FeedDto(
                author = AuthorDto(LabelDto("Test Author"), LabelDto("uri")),
                entries = listOf(
                    EntryDto(
                        name = LabelDto("Test Album"),
                        images = emptyList(),
                        itemCount = null,
                        price = PriceDto("10", PriceAttributesDto("10", "USD")),
                        contentType = ContentTypeDto(TypeLabelDto(LabelTermDto("Test Type", "Test Type")), LabelTermDto("Test Type", "Test Type")),
                        rights = LabelDto("Test Rights"),
                        title = LabelDto("Title"),
                        link = LinkDto(LinkAttributesDto("rel", "type", "http://link.com")),
                        id = IdDto("id", IdAttributesDto("1")),
                        artist = ArtistDto("Test Artist", null),
                        category = CategoryDto(
                            CategoryAttributesDto(
                                "1",
                                "term",
                                "scheme",
                                "label"
                            )
                        ),
                        releaseDate = ReleaseDateDto("2023-01-01", LabelDto("label"))
                    )
                )
            )
        )
    }
}
