package com.antonioleiva.flowworkshop

import android.app.Application
import androidx.room.Room
import com.antonioleiva.flowworkshop.data.db.MovieDatabase

class MoviesApp : Application() {

    lateinit var db: MovieDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            MovieDatabase::class.java, "movie-db"
        ).build()
    }
}