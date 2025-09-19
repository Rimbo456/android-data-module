package com.example.android_data_module.data.movie.model

import com.example.android_data_module.data.movie.datasource.local.MovieEntity
import com.example.android_data_module.data.movie.datasource.remote.dto.MovieDto

fun MovieDto.toEntity() = MovieEntity (
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath
)

fun MovieEntity.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath
)