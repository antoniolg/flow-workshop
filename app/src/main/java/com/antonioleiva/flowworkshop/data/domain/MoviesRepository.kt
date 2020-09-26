package com.antonioleiva.flowworkshop.data.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    fun getMovies(): Flow<List<Movie>> = flow {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.getMovies()
            localDataSource.saveMovies(movies)
        }

        while (true) {
            delay(2000)
            emit(localDataSource.getMovies().shuffled())
        }
    }
}

interface RemoteDataSource {
    suspend fun getMovies(): List<Movie>
}

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun getMovies(): List<Movie>
}