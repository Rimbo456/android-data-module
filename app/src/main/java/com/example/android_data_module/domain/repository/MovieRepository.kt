package com.example.android_data_module.domain.repository

import com.example.android_data_module.domain.util.Resource
import com.example.android_data_module.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}