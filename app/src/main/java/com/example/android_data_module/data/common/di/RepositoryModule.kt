package com.example.android_data_module.data.common.di

import com.example.android_data_module.data.common.datastore.UserSettingRepositoryImpl
import com.example.android_data_module.data.movie.repository.MovieRepositoryImpl
import com.example.android_data_module.domain.repository.MovieRepository
import com.example.android_data_module.domain.repository.UserSettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindUserSettingRepository(
        userSettingRepositoryImpl: UserSettingRepositoryImpl
    ): UserSettingRepository

}