package com.example.android_data_module.data.movie.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
)