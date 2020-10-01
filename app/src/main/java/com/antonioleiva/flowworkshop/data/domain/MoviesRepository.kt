package com.antonioleiva.flowworkshop.data.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout

private const val PAGE_SIZE = 20
private const val PAGE_THRESHOLD = 10

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    fun getMovies(): Flow<List<Movie>> = localDataSource.getMovies()

    suspend fun checkRequireNewPage(lastVisible: Int) {
        val size = localDataSource.size()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = withTimeout(5_000) { remoteDataSource.getMovies(page) }
            localDataSource.saveMovies(newMovies)
        }
    }
}

interface RemoteDataSource {
    suspend fun getMovies(page: Int): List<Movie>
}

interface LocalDataSource {
    suspend fun size(): Int
    suspend fun saveMovies(movies: List<Movie>)
    fun getMovies(): Flow<List<Movie>>
}