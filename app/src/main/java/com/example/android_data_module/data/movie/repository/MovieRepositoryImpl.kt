package com.example.android_data_module.data.movie.repository

import NetworkBoundResource
import com.example.android_data_module.domain.util.Resource
import com.example.android_data_module.data.movie.datasource.local.MovieDao
import com.example.android_data_module.data.movie.datasource.remote.MovieApiService
import com.example.android_data_module.data.movie.datasource.remote.dto.MovieDto
import com.example.android_data_module.domain.model.Movie
import com.example.android_data_module.data.movie.mapper.toDomain
import com.example.android_data_module.data.movie.mapper.toEntity
import com.example.android_data_module.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao
): MovieRepository {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieDto>>() {
            override fun loadFromDb(): Flow<List<Movie>> {
                return movieDao.getAllMovies().map { list ->
                    list.map { it.toDomain() }
                }
            }
            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }
            override suspend fun createCall(): List<MovieDto> {
                val res = movieApiService.getPopularMovies()
                if (!res.isSuccessful) {
                    return res.body()?.results ?: emptyList()
                } else {
                    throw Exception("Api error code: ${res.code()}")
                }
            }
            override suspend fun saveCallResult(data: List<MovieDto>) {
                movieDao.insertMovies(data.map { it.toEntity() })
            }

        }.asFlow()
    }
}