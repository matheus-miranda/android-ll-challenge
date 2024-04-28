package com.luizalabs.registration.list

import com.luizalabs.registration.domain.repository.DeliveryRepository
import com.luizalabs.registration.list.ListViewModelTestHelper.form
import com.luizalabs.registration.presentation.list.ListUiState
import com.luizalabs.registration.presentation.list.ListViewModel
import com.luizalabs.testing.MainDispatcherRule
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ListViewModel
    private val repository: DeliveryRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = ListViewModel(repository)
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        assertEquals(ListUiState.Error, viewModel.uiState.value)
    }

    @Test
    fun uiState_whenEmptyList_thenEmitEmptyState() = runTest {
        val vmStateCollector = collectJob()
        every { repository.getForms() } returns flowOf(emptyList())
        advanceTimeBy(5_000)

        assertEquals(ListUiState.Empty, viewModel.uiState.value)
        vmStateCollector.cancel()
    }

    @Test
    fun uiState_whenSuccess_emitDeliveryList() = runTest {
        val vmStateCollector = collectJob()
        every { repository.getForms() } returns flowOf(listOf(form))
        advanceTimeBy(5_000)

        assertEquals(ListUiState.Success(listOf(form)), viewModel.uiState.value)
        vmStateCollector.cancel()
    }

    @Test
    fun uiState_whenException_emitError() = runTest {
        val vmStateCollector = collectJob()
        every { repository.getForms() } throws Exception()
        advanceTimeBy(5_000)

        assertEquals(ListUiState.Error, viewModel.uiState.value)
        vmStateCollector.cancel()
    }

    private fun TestScope.collectJob(): Job {
        val vmCollector = mutableListOf<ListUiState>()
        val collectJob = launch {
            viewModel.uiState.collect { state ->
                vmCollector.add(state)
            }
        }
        return collectJob
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun tearDown() {
            clearAllMocks()
        }
    }
}
