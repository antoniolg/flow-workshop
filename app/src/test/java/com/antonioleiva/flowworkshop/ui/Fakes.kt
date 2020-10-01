package com.antonioleiva.flowworkshop.ui

import com.antonioleiva.flowworkshop.data.domain.LocalDataSource
import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.domain.RemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val fakeMovies = listOf(
    Movie(1, "Title 1", "poster1", 7.0),
    Movie(2, "Title 2", "poster2", 7.0),
    Movie(3, "Title 3", "poster3", 7.0),
    Movie(4, "Title 4", "poster4", 7.0),
)

class FakeLocalDataSource : LocalDataSource {

    private val movies = mutableListOf<Movie>()

    override suspend fun size(): Int = movies.size

    override suspend fun saveMovies(movies: List<Movie>) {
        this.movies += movies
    }

    override fun getMovies(): Flow<List<Movie>> = flowOf(movies)

}

class FakeRemoteDataSource(
    private val movies: List<Movie> = emptyList(),
    private val delay: Long = 0
) : RemoteDataSource {

    override suspend fun getMovies(page: Int): List<Movie> {
        delay(delay)
        return movies
    }
}
