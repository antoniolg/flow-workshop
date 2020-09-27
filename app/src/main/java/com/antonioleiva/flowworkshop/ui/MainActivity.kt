package com.antonioleiva.flowworkshop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antonioleiva.flowworkshop.R
import com.antonioleiva.flowworkshop.data.db.RoomDataSource
import com.antonioleiva.flowworkshop.data.domain.MoviesRepository
import com.antonioleiva.flowworkshop.data.server.TheMovieDbDataSource
import com.antonioleiva.flowworkshop.databinding.ActivityMainBinding
import com.antonioleiva.flowworkshop.ui.common.app
import com.antonioleiva.flowworkshop.ui.common.getViewModel
import com.antonioleiva.flowworkshop.ui.common.visible

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel = getViewModel(::buildViewModel)

            val moviesAdapter = MoviesAdapter()

            viewModel.spinner.observe(this@MainActivity, { progress.visible = it })
            viewModel.movies.observe(this@MainActivity, { moviesAdapter.submitList(it) })

            val layoutManager = recycler.layoutManager as GridLayoutManager

            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    viewModel.lastVisible.value = layoutManager.findLastVisibleItemPosition()
                }
            })

            recycler.adapter = moviesAdapter
        }

    }

    private fun buildViewModel() = MainViewModel(
        MoviesRepository(
            RoomDataSource(app.db),
            TheMovieDbDataSource(getString(R.string.api_key))
        )
    )
}