package com.example.android_data_module.domain.repository

import com.example.android_data_module.data.common.util.Resource
import com.example.android_data_module.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<Resource<List<Movie>>>
}