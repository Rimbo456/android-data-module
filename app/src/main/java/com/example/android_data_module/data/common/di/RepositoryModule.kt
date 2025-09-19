package com.example.android_data_module.data.common.di

import com.example.android_data_module.data.movie.datasource.local.MovieDao
import com.example.android_data_module.data.movie.datasource.remote.MovieApiService
import com.example.android_data_module.data.movie.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApiService, dao: MovieDao): MovieRepository {
        return MovieRepository(api, dao)
    }
}