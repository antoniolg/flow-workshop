package com.antonioleiva.myapplication.data

import com.antonioleiva.myapplication.data.domain.Movie
import com.antonioleiva.myapplication.data.server.Movie as ServerMovie

fun ServerMovie.toDomainMovie(): Movie =
    Movie(
        0,
        title,
        posterPath,
        voteAverage,
    )