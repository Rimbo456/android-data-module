package com.example.android_data_module.data.movie.datasource.remote.interceptor

import com.example.android_data_module.data.common.datastore.UserSettingRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageInterceptor @Inject constructor(
    private val userSettingRepositoryImpl: UserSettingRepositoryImpl
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val currentLanguage = runBlocking {
            userSettingRepositoryImpl.languageFlow.first() ?: "en-US"
        }
        val url = original.url.newBuilder()
            .addQueryParameter("language", currentLanguage)
            .build()
        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}