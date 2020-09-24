package com.antonioleiva.myapplication.data.server

import com.antonioleiva.myapplication.data.domain.Movie
import com.antonioleiva.myapplication.data.domain.RemoteDataSource
import com.antonioleiva.myapplication.data.toDomainMovie

class TheMovieDbDataSource(private val apiKey: String) : RemoteDataSource {

    override fun getMovies(): List<Movie> =
        TheMovieDb.service
            .listPopularMoviesAsync(apiKey)
            .execute()
            .body()!!
            .results
            .map { it.toDomainMovie() }
}