package com.antonioleiva.flowworkshop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT COUNT(id) FROM Movie")
    suspend fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<Movie>)

}