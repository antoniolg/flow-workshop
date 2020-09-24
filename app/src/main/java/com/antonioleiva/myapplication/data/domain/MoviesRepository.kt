package com.antonioleiva.myapplication.data.domain

private const val movieCover = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg"

class MoviesRepository {

    fun getMovies(): List<Movie> = listOf(
        Movie(1, "Title 1", movieCover, 7.0),
        Movie(1, "Title 1", movieCover, 7.0),
        Movie(1, "Title 1", movieCover, 7.0),
        Movie(1, "Title 1", movieCover, 7.0),
        Movie(1, "Title 1", movieCover, 7.0),
        Movie(1, "Title 1", movieCover, 7.0),
    )
}