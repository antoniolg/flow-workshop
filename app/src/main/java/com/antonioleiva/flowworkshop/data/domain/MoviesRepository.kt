package com.antonioleiva.flowworkshop.data.domain

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
) {

    fun getMovies(): List<Movie> = remoteDataSource.getMovies()
}

interface RemoteDataSource {
    fun getMovies(): List<Movie>
}