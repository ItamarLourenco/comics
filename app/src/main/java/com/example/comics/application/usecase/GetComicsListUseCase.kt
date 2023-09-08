package com.example.comics.application.usecase;

import com.example.comics.application.domain.repository.ComicsRepository
import com.example.comics.application.domain.model.ItemModel
import retrofit2.Response
import javax.inject.Inject

class GetComicsListUseCase @Inject constructor(
    private val comicsRepository: ComicsRepository
)
{
    suspend fun execute(): Response<ItemModel> {
        return comicsRepository.getComics()
    }
}
