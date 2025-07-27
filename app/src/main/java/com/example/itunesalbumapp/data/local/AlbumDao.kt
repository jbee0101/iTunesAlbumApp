package com.example.itunesalbumapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Entity class representing an album in the database.
 */
@Dao
interface AlbumDao {
    /**
     * Retrieves all albums from the database.
     *
     * @return A [Flow] emitting a list of [AlbumEntity] objects.
     */
    @Query("SELECT * FROM albums")
    fun getAll(): Flow<List<AlbumEntity>>

    /**
     * Inserts a list of albums into the database.
     *
     * @param albums The list of [AlbumEntity] objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<AlbumEntity>)
}
