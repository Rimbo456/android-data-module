package com.example.android_data_module.ui.feature_movie.state

import com.example.android_data_module.domain.model.Movie

data class MovieListUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)