package com.antonioleiva.flowworkshop.data.domain

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    fun getMovies(): List<Movie> {
        if (localDataSource.isEmpty()) {
            val movies =
                remoteDataSource.getMovies()
            localDataSource.saveMovies(movies)
        }

        return localDataSource.getMovies()
    }
}

interface RemoteDataSource {
    fun getMovies(): List<Movie>
}

interface LocalDataSource {
    fun isEmpty(): Boolean
    fun saveMovies(movies: List<Movie>)
    fun getMovies(): List<Movie>
}