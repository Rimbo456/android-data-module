package com.example.android_data_module.ui.feature_movie.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android_data_module.ui.feature_movie.components.ListMovieContent
import com.example.android_data_module.ui.feature_movie.components.SimpleButtonDropdown
import com.example.android_data_module.ui.feature_movie.viewmodel.MovieListViewModel
import kotlinx.coroutines.launch

@Composable
fun ListMoviesScreen(
    viewModel: MovieListViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateLanguage("vi-VN")
                        viewModel.loadPopularMovie(forceRefresh = true)
                    }
                }
            ) {
                Text("viet")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateLanguage("en-US")
                        viewModel.loadPopularMovie(forceRefresh = true)
                    }
                }
            ) {
                Text("anh")
            }
        }
        ListMovieContent(
            uiState = uiState,
            onRefresh = viewModel::loadPopularMovie
        )
    }

}