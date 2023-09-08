package com.example.comics.application.feature.comics

import android.content.Context
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comics.application.domain.model.DataModel
import com.example.comics.application.domain.model.ItemModel
import com.example.comics.application.domain.model.ResultModel
import com.example.comics.application.domain.model.ThumbnailModel
import com.example.comics.application.usecase.GetComicsListUseCase
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response
import com.example.comics.R
import kotlinx.coroutines.runBlocking


@HiltAndroidTest
class ComicsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    private val context: Context = mock(Context::class.java)
    private val getComicsListUseCase: GetComicsListUseCase = mock(GetComicsListUseCase::class.java)

    private lateinit var viewModel: ComicsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ComicsViewModel(context, getComicsListUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }


    private val fakeComicsList = listOf(ResultModel(
            id = 1,
            title = "title",
            description = "description ",
            image = "http://example.com/1.jpg",
            thumbnail = ThumbnailModel(path = "http://example.com/1", extension = "jpg")
        ),ResultModel(
            id = 2,
            title = "title",
            description = "description ",
            image = "http://example.com/2.jpg",
            thumbnail = ThumbnailModel(path = "http://example.com/2", extension = "jpg")
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Fetch Items success`() = runBlocking {
        val itemModel = ItemModel(
            data= DataModel(
                results = fakeComicsList
            )
        )
        val mockResponse = Response.success(itemModel)
        `when`(getComicsListUseCase.execute()).thenReturn(mockResponse)

        viewModel.fetchItems()

        Assert.assertFalse(viewModel.isLoading.value)
        Assert.assertEquals(fakeComicsList.size, viewModel.comicsState.value.size)
        Assert.assertNull(viewModel.errorState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get Item By Id`() = runBlocking {
        val itemModel = ItemModel(
            data= DataModel(
                results = fakeComicsList
            )
        )
        val mockResponse = Response.success(itemModel)
        `when`(getComicsListUseCase.execute()).thenReturn(mockResponse)

        viewModel.fetchItems()
        Assert.assertEquals(viewModel.getItemById("1"), fakeComicsList[0])
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Fetch Items failure`() = runBlocking {

        val mockResponse = Response.error<ItemModel>(404, mock(ResponseBody::class.java))
        `when`(getComicsListUseCase.execute()).thenReturn(mockResponse)

        `when`(context.getString(R.string.error_fetching_items)).thenReturn("Ocorreu um erro, por favor tente novamente mais tarde")

        viewModel.fetchItems()

        Assert.assertFalse(viewModel.isLoading.value)

        Assert.assertEquals("Ocorreu um erro, por favor tente novamente mais tarde", viewModel.errorState.value)
    }
}