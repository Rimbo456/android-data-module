package com.example.android_data_module.data.common.di

import com.example.android_data_module.data.common.datastore.UserSettingRepositoryImpl
import com.example.android_data_module.data.common.util.Constants
import com.example.android_data_module.data.movie.datasource.remote.MovieApiService
import com.example.android_data_module.data.movie.datasource.remote.interceptor.AuthInterceptor
import com.example.android_data_module.data.movie.datasource.remote.interceptor.LanguageInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLanguageInterceptor(userSettingRepositoryImpl: UserSettingRepositoryImpl): LanguageInterceptor {
        return LanguageInterceptor(userSettingRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        languageInterceptor: LanguageInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(languageInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}