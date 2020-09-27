package com.antonioleiva.flowworkshop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonioleiva.flowworkshop.data.domain.Movie
import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import com.antonioleiva.flowworkshop.ui.common.collectFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner

    val movies: Flow<List<Movie>> get() = repository.getMovies()

    val lastVisible = MutableStateFlow(0)

    init {
        viewModelScope.collectFlow(lastVisible, ::notifyLastVisible)
    }

    private suspend fun notifyLastVisible(lastVisible: Int) {
        repository.checkRequireNewPage(lastVisible)
        _spinner.value = false
    }
}