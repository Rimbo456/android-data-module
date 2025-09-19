package com.example.android_data_module.data.movie.repository

import com.example.android_data_module.data.common.util.Resource
import com.example.android_data_module.data.movie.datasource.local.MovieDao
import com.example.android_data_module.data.movie.datasource.remote.MovieApiService
import com.example.android_data_module.data.movie.model.Movie
import com.example.android_data_module.data.movie.model.toDomain
import com.example.android_data_module.data.movie.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao
) {
    fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading)

        val cache = movieDao.getAllMovies().map { it.toDomain() }
        if (cache.isNotEmpty()) emit(Resource.Success(cache))

        try {
            val res = movieApiService.getPopularMovies()
            if (res.isSuccessful) {
                res.body()?.let { body ->
                    val entities = body.results.map { it.toEntity() }
                    movieDao.insertMovies(entities)
                    emit(Resource.Success(entities.map { it.toDomain() }))
                } ?: emit(Resource.Error("Response body is null"))
            } else {
                val errorBody = res.errorBody()?.string() ?: "No error body"
                val errorMessage = "HTTP ${res.code()}: ${res.message()}. Body: $errorBody"
                emit(Resource.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }
}