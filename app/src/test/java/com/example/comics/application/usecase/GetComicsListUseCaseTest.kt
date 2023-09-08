package com.example.comics.application.usecase

import com.example.comics.application.domain.model.DataModel
import com.example.comics.application.domain.model.ItemModel
import com.example.comics.application.domain.model.ResultModel
import com.example.comics.application.domain.model.ThumbnailModel
import com.example.comics.application.domain.repository.ComicsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

class GetComicsListUseCaseTest {
    private lateinit var useCase: GetComicsListUseCase
    private lateinit var comicsRepository: ComicsRepository

    @Before
    fun setUp() {
        comicsRepository = mock(ComicsRepository::class.java)
        useCase = GetComicsListUseCase(comicsRepository)
    }

    @Test
    fun `return comics list from use case`() = runBlocking {
        val fakeComicsList = listOf(
                ResultModel(
                id = 1,
                title = "title",
                description = "description ",
                image = "http://example.com/1.jpg",
                thumbnail = ThumbnailModel(path = "http://example.com/1", extension = "jpg")
            ), ResultModel(
                id = 2,
                title = "title",
                description = "description ",
                image = "http://example.com/2.jpg",
                thumbnail = ThumbnailModel(path = "http://example.com/2", extension = "jpg")
            )
        )
        val itemModel = ItemModel(
            data= DataModel(
                results = fakeComicsList
            )
        )

        val expectedResponse = Response.success(itemModel)
        `when`(comicsRepository.getComics()).thenReturn(expectedResponse)

        val result = useCase.execute()

        verify(comicsRepository).getComics()
        assert(result.isSuccessful)
        assert(result.body() != null)
    }
}