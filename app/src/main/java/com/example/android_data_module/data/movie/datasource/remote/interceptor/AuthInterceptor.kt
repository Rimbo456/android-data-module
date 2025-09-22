package com.example.android_data_module.data.movie.datasource.remote.interceptor

import com.example.android_data_module.data.common.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)
            .build()
        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}