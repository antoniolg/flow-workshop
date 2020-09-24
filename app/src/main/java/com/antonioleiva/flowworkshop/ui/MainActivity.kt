package com.antonioleiva.flowworkshop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antonioleiva.flowworkshop.R
import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import com.antonioleiva.flowworkshop.data.server.TheMovieDbDataSource
import com.antonioleiva.flowworkshop.databinding.ActivityMainBinding
import com.antonioleiva.flowworkshop.ui.common.getViewModel
import com.antonioleiva.flowworkshop.ui.common.visible

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel = getViewModel {
                MainViewModel(
                    MoviesRepository(
                        TheMovieDbDataSource(getString(R.string.api_key))
                    )
                )
            }

            val moviesAdapter = MoviesAdapter()

            viewModel.spinner.observe(this@MainActivity, { progress.visible = it })
            viewModel.movies.observe(this@MainActivity, { moviesAdapter.submitList(it) })

            recycler.adapter = moviesAdapter
        }


    }
}