package com.example.comics.application.feature.comics

import android.content.Context
import com.example.comics.application.usecase.GetComicsListUseCase
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before

class ComicsViewModelTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockRepository: GetComicsListUseCase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: ComicsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = ComicsViewModel(mockContext, mockRepository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `fetchItems should update comicsState on success`() = testScope.runBlockingTest {
        // Given
        val mockResult = ResultModel(/* Initialize your mock ResultModel here */)
        val mockResultsList = listOf(mockResult)
        val mockSuccessResponse = /* Initialize your success response mock here */
            Mockito.`when`(mockRepository.execute()).thenReturn(mockSuccessResponse)

        // When
        viewModel.fetchItems()

        // Then
        assert(viewModel.isLoading.value == false)
        assert(viewModel.comicsState.value == mockResultsList)
        assert(viewModel.errorState.value == null)
    }

    @Test
    fun `fetchItems should handle network error`() = testScope.runBlockingTest {
        // Given
        val mockErrorResponse = /* Initialize your error response mock here */
            Mockito.`when`(mockRepository.execute()).thenThrow(Exception("Network error"))

        // When
        viewModel.fetchItems()

        // Then
        assert(viewModel.isLoading.value == false)
        assert(viewModel.comicsState.value.isEmpty())
        assert(viewModel.errorState.value != null)
    }
}