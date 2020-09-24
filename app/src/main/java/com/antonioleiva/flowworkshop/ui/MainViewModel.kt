package com.antonioleiva.flowworkshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(repository: MoviesRepository) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _spinner = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> get() = _spinner

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        launch {
            _spinner.value = true
            _movies.value = repository.getMovies()
            _spinner.value = false
        }
    }

    override fun onCleared() {
        job.cancel()
    }

}