package com.example.android_data_module.ui.feature_movie.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android_data_module.ui.feature_movie.components.ListMovieContent
import com.example.android_data_module.ui.feature_movie.viewmodel.MovieListViewModel

@Composable
fun ListMoviesScreen(
    viewModel: MovieListViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ListMovieContent(
        uiState = uiState,
        onRefresh = viewModel::loadPopularMovie
    )
}