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

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAlbumRepository(
        rickMortyApi: AlbumApiService,
        appDatabase: AlbumDatabase
    ): AlbumRepository {
        return AlbumRepositoryImpl(rickMortyApi, appDatabase)
    }
}