package com.example.reallynews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reallynews.data.remote.NewsRepository
import com.example.reallynews.domain.model.NewsModel
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsState = MutableLiveData<NewsState>()
    val newsState: LiveData<NewsState> = _newsState

    fun fetchTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            _newsState.value = NewsState.Loading
            repository.getTopHeadlines(country, apiKey).fold(
                onSuccess = { _newsState.value = NewsState.Success(it) },
                onFailure = { _newsState.value = NewsState.Error(it.message ?: "Unknown Error") }
            )
        }
    }
}

sealed class NewsState {
    data object Loading : NewsState()
    data class Success(val data: List<NewsModel>) : NewsState()
    data class Error(val message: String) : NewsState()
}
