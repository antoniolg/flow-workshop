package com.antonioleiva.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antonioleiva.myapplication.data.domain.Movie
import com.antonioleiva.myapplication.databinding.ActivityMainBinding

private const val movieCover = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val moviesAdapter = MoviesAdapter()
            recycler.adapter = moviesAdapter
            moviesAdapter.submitList(
                listOf(
                    Movie(1, "Title 1", movieCover, 7.0),
                    Movie(1, "Title 1", movieCover, 7.0),
                    Movie(1, "Title 1", movieCover, 7.0),
                    Movie(1, "Title 1", movieCover, 7.0),
                    Movie(1, "Title 1", movieCover, 7.0),
                    Movie(1, "Title 1", movieCover, 7.0),
                )
            )
        }


    }
}