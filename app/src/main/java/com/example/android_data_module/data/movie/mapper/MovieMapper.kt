package com.example.android_data_module.data.movie.mapper

import com.example.android_data_module.data.movie.datasource.local.MovieEntity
import com.example.android_data_module.data.movie.datasource.remote.dto.MovieDto
import com.example.android_data_module.domain.model.Movie

fun MovieDto.toEntity() = MovieEntity (
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage
)

fun MovieEntity.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage
)