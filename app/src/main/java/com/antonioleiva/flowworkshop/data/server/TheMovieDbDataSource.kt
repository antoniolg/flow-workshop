package com.antonioleiva.flowworkshop.data.server

import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.domain.RemoteDataSource
import com.antonioleiva.flowworkshop.data.toDomainMovie

class TheMovieDbDataSource(private val apiKey: String) : RemoteDataSource {

    override fun getMovies(): List<Movie> =
        TheMovieDb.service
            .listPopularMoviesAsync(apiKey)
            .execute()
            .body()!!
            .results
            .map { it.toDomainMovie() }
}