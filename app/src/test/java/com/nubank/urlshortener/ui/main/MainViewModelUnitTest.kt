package com.nubank.urlshortener.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.repository.UrlShortenerRepositoryImpl
import com.nubank.urlshortener.ui.viewmodel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelUnitTest {


    @RelaxedMockK
    private lateinit var mockUrlShortenerRepository: UrlShortenerRepositoryImpl

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(mockUrlShortenerRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when viewModel calls create alias, it adds the value to the alias list`() = runTest {

        // Given
        val alias = Alias(
            alias = "https://example.com",
            links = Alias.Links(
                self = "https://example.com",
                short = "https://example.com/alias/123456789"
            )
        )
        val originalUrl = "https://example.com"
        coEvery { mockUrlShortenerRepository.createAlias(originalUrl) } returns alias


        // When
        viewModel.createAlias(originalUrl)

        // Then
        assertEquals(viewModel.aliases.value!![0].links!!.self, alias.links!!.self)
    }
}