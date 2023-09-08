package com.example.comics.application.feature.comics

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.R
import com.example.comics.application.domain.model.ResultModel
import com.example.comics.application.domain.repository.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: ComicsRepository
) : ViewModel() {

    val comicsState = MutableStateFlow<List<ResultModel>>(emptyList())
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    val errorState = MutableStateFlow<String?>(null)

    init{
        fetchItems()
    }
    fun fetchItems() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val comics = repository.getComics()
                _isLoading.value = false
                when {
                    comics.isSuccessful -> {
                        comics.body()?.data?.results?.also { comicsState.emit(it) }
                    }
                    else -> {
                        handleNetworkError()
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
                handleNetworkError()
                Log.e("ComicsViewModel", "Error fetching data", e)
            }
        }
    }

    fun getItemById(itemId: String?): ResultModel? {
        return comicsState.value.find { it.id == itemId?.toInt() }
    }

    private suspend fun handleNetworkError() {
        errorState.emit(context.getString(R.string.error_fetching_items))
    }
}



