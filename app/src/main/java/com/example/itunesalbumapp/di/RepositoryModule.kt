package com.example.itunesalbumapp.di

import com.example.itunesalbumapp.data.repository.AlbumRepositoryImpl
import com.example.itunesalbumapp.data.local.AlbumDatabase
import com.example.itunesalbumapp.data.remote.AlbumApiService
import com.example.itunesalbumapp.domain.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that provides dependencies for the repository.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides an instance of [AlbumRepository].
     *
     * @param albumApiService The [AlbumApiService] instance.
     * @param appDatabase The [AlbumDatabase] instance.
     * @return The [AlbumRepository] instance.
     */
    @Provides
    @Singleton
    fun provideAlbumRepository(
        albumApiService: AlbumApiService,
        appDatabase: AlbumDatabase
    ): AlbumRepository {
        return AlbumRepositoryImpl(albumApiService, appDatabase)
    }
}