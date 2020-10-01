package com.antonioleiva.flowworkshop.ui

import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test(expected = TimeoutCancellationException::class)
    fun `After timeout, an exception is thrown`() = runBlockingTest {
        val repository = MoviesRepository(
            FakeLocalDataSource(),
            FakeRemoteDataSource(delay = 6_000)
        )

        repository.checkRequireNewPage(0)

        advanceTimeBy(5_000)
    }
}