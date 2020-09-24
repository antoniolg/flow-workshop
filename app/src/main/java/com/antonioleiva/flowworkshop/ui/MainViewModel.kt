package com.antonioleiva.flowworkshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import com.antonioleiva.flowworkshop.ui.common.BACKGROUND

class MainViewModel(repository: MoviesRepository) : ViewModel() {

    private val _spinner = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> get() = _spinner

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        BACKGROUND.submit {
            val result = repository.getMovies()
            _movies.postValue(result)
            _spinner.postValue(false)
        }

    }

}