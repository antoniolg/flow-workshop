package com.antonioleiva.flowworkshop.ui

import androidx.lifecycle.*
import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _spinner = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> get() = _spinner

    val movies: LiveData<List<Movie>> get() = repository.getMovies().asLiveData()

    val lastVisible = MutableStateFlow(0)

    init {
        _spinner.value = true

        viewModelScope.launch {
            lastVisible.collect { value ->
                repository.checkRequireNewPage(value)
                _spinner.value = false
            }
        }
    }
}