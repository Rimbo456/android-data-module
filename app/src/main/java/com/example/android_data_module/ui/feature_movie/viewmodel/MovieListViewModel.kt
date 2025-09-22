package com.example.android_data_module.ui.feature_movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_data_module.domain.model.Movie
import com.example.android_data_module.domain.util.Resource
import com.example.android_data_module.domain.repository.MovieRepository
import com.example.android_data_module.ui.feature_movie.state.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init {
        loadPopularMovie()
    }

    fun loadPopularMovie() {
        movieRepository.getPopularMovies()
            .onEach { resource ->
                val newState = when (resource) {
                    is Resource.Loading -> _uiState.value.copy(
                        isLoading = true,
                        movies = resource.data ?: emptyList(),
                        errorMessage = null
                    )
                    is Resource.Success -> _uiState.value.copy(
                        isLoading = false,
                        movies = resource.data,
                        errorMessage = null
                    )
                    is Resource.Error -> _uiState.value.copy(
                        isLoading = false,
                        movies = resource.data ?: emptyList(),
                        errorMessage = resource.message
                    )
                }
                _uiState.value = newState
            }
            .launchIn(viewModelScope)
    }
}
