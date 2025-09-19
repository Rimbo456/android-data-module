package com.example.android_data_module

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.android_data_module.data.common.util.Resource
import com.example.android_data_module.data.movie.repository.MovieRepository
import com.example.android_data_module.ui.theme.AndroiddatamoduleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroiddatamoduleTheme {

            }
        }

        lifecycleScope.launch {
            movieRepository.getPopularMovies().collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        Log.d("MainActivity", "Loading...")
                    }
                    is Resource.Success -> {
                        Log.d("MainActivity", "Movies: ${state.data.size}")
                        state.data.forEach { movie ->
                            Log.d("MainActivity", "Movie: ${movie.title}")
                        }
                    }
                    is Resource.Error -> {
                        Log.d("MainActivity", "Error: ${state.message}")
                    }
                }
            }
        }
    }
}