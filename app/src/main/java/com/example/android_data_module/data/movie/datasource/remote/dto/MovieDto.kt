package com.example.android_data_module.data.movie.datasource.remote.dto

data class MovieDto (
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
)