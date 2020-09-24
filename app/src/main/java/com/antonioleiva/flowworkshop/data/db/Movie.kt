package com.antonioleiva.flowworkshop.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double
)