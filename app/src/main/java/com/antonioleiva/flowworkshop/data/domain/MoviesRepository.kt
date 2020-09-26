package com.antonioleiva.flowworkshop.data.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    fun getMovies(): Flow<List<Movie>> = localDataSource.getMovies()
        .transform { movies ->
            if (movies.isEmpty()) {
                val newMovies = remoteDataSource.getMovies()
                localDataSource.saveMovies(newMovies)
            }
            emit(movies)
        }
}

interface RemoteDataSource {
    suspend fun getMovies(): List<Movie>
}

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveMovies(movies: List<Movie>)
    fun getMovies(): Flow<List<Movie>>
}