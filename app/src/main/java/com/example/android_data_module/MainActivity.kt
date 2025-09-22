package com.example.android_data_module

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.android_data_module.domain.util.Resource
import com.example.android_data_module.domain.repository.MovieRepository
import com.example.android_data_module.domain.repository.UserSettingRepository
import com.example.android_data_module.ui.feature_movie.screen.ListMoviesScreen
import com.example.android_data_module.ui.feature_movie.viewmodel.MovieListViewModel
import com.example.android_data_module.ui.theme.AndroiddatamoduleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var movieRepository: MovieRepository
    @Inject lateinit var userSettingRepository: UserSettingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MovieListViewModel(
            movieRepository,
            userSettingRepository
        )

        enableEdgeToEdge()
        setContent {
            AndroiddatamoduleTheme {
                ListMoviesScreen(viewModel = viewModel)
            }
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->

            }
        }
    }
}