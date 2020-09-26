package com.antonioleiva.flowworkshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.domain.MoviesRepository

class MainViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _spinner = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> get() = _spinner

    val movies: LiveData<List<Movie>> get() = repository.getMovies().asLiveData()

    init {
        _spinner.value = false
    }
}