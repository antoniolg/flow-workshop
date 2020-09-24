package com.antonioleiva.flowworkshop.data


import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.db.Movie as RoomMovie
import com.antonioleiva.flowworkshop.data.server.Movie as ServerMovie

fun ServerMovie.toDomainMovie(): Movie =
    Movie(
        0,
        title,
        posterPath,
        voteAverage,
    )

fun Movie.toRoomMovie(): RoomMovie =
    RoomMovie(
        id,
        title,
        posterPath,
        voteAverage,
    )

fun RoomMovie.toDomainMovie(): Movie = Movie(
    id,
    title,
    posterPath,
    voteAverage,
)