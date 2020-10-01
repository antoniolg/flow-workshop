package com.antonioleiva.flowworkshop.ui

import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Listening to movies Flow emits the list of movies from the server`() = runBlockingTest {
        val repository = MoviesRepository(FakeLocalDataSource(), FakeRemoteDataSource(fakeMovies))
        val vm = MainViewModel(repository)

        vm.movies.collect {
            Assert.assertEquals(fakeMovies, it)
        }
    }
}