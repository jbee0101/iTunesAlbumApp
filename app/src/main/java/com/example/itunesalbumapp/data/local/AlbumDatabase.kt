package com.example.itunesalbumapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Represents the Room database for albums.
 */
@Database(entities = [AlbumEntity::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {
    /**
     * Provides access to the [AlbumDao] for database operations related to albums.
     *
     * @return The [AlbumDao] instance.
     */
    abstract fun albumDao(): AlbumDao
}
