package com.antonioleiva.flowworkshop.data.domain

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    suspend fun getMovies(): List<Movie> {
        if (localDataSource.isEmpty()) {
            val movies =
                remoteDataSource.getMovies()
            localDataSource.saveMovies(movies)
        }

        return localDataSource.getMovies()
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