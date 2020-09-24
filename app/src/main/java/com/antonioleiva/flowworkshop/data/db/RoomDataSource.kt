package com.antonioleiva.flowworkshop.data.db

import com.antonioleiva.flowworkshop.data.domain.LocalDataSource
import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.toDomainMovie
import com.antonioleiva.flowworkshop.data.toRoomMovie

class RoomDataSource(db: MovieDatabase) : LocalDataSource {

    private val movieDao = db.movieDao()

    override fun isEmpty(): Boolean = movieDao.movieCount() <= 0

    override fun saveMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies.map { it.toRoomMovie() })
    }

    override fun getMovies(): List<Movie> = movieDao.getAll().map { it.toDomainMovie() }
}