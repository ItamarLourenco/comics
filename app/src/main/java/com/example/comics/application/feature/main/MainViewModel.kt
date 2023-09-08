package com.example.comics.application.feature.main

import android.util.Log

import androidx.lifecycle.viewModelScope
import com.example.comics.repository.Api
import com.example.comics.application.domain.model.ResultModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.example.comics.application.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    val itemsState = MutableStateFlow<List<ResultModel>>(emptyList())

    init{
        fetchItems()
    }
    fun fetchItems() {
        viewModelScope.launch {
            try {
                val resultsFlow: Api? = repository.getApi()?.create(Api::class.java)

                val getComics = resultsFlow?.getComics(
                    apikey = "b7e14bab409c70a5c4e7c2b319c09d7b",
                    ts = "1682982412",
                    hash = "3482f01e9bf207a437a4b621c91339ad"
                )
                getComics?.data?.let { itemsState.emit(it.results) }
            } catch (e: Exception) {
                Log.i("DEBUG", e.message.toString())
            }
        }
    }
}



