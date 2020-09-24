package com.antonioleiva.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.antonioleiva.myapplication.R
import com.antonioleiva.myapplication.data.domain.MoviesRepository
import com.antonioleiva.myapplication.data.server.TheMovieDbDataSource
import com.antonioleiva.myapplication.databinding.ActivityMainBinding
import getViewModel
import visible

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel = getViewModel { MainViewModel(MoviesRepository(
                TheMovieDbDataSource(getString(R.string.api_key))
            )) }

            val moviesAdapter = MoviesAdapter()

            viewModel.spinner.observe(this@MainActivity, { progress.visible = it })
            viewModel.movies.observe(this@MainActivity, { moviesAdapter.submitList(it) })

            recycler.adapter = moviesAdapter
        }


    }
}