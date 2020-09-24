package com.antonioleiva.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonioleiva.myapplication.data.domain.Movie
import com.antonioleiva.myapplication.data.domain.MoviesRepository

class MainViewModel(repository: MoviesRepository) : ViewModel() {

    private val _spinner = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> get() = _spinner

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        _spinner.value = true
        _movies.value = repository.getMovies()
        _spinner.value = false
    }

}