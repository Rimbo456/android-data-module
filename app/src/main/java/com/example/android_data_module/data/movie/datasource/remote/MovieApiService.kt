package com.example.android_data_module.data.movie.datasource.remote

import com.example.android_data_module.data.movie.datasource.remote.dto.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    ): Response<MovieResponseDto>
}