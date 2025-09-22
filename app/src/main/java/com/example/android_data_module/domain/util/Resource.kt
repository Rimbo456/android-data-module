package com.example.android_data_module.domain.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val message: String, val data: T? = null) : Resource<T>()
    class Loading<out T>(val data: T? = null) : Resource<T>()
}