package com.example.itunesalbumapp.di

import android.content.Context
import androidx.room.Room
import com.example.itunesalbumapp.data.local.AlbumDao
import com.example.itunesalbumapp.data.local.AlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideCharacterDao(db: AlbumDatabase): AlbumDao = db.albumDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AlbumDatabase {
        return Room.databaseBuilder(
            context,
            AlbumDatabase::class.java,
            "album_db"
        ).build()
    }
}
