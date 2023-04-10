package com.nubank.urlshortener.data.repository

import com.nubank.urlshortener.data.model.Alias
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UrlShortenerRepositoryTest {

    @MockK
    private lateinit var mockUrlShortenerRepository: UrlShortenerRepository

    @Before

    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when the api call is successful, the repository should return the alias`() = runBlocking {
        // Given
        val alias = Alias(
            alias = "https://example.com",
            links = Alias.Links(
                self = "https://example.com",
                short = "https://example.com/alias/123456789"
            )
        )
        val originalUrl = "https://example.com"

        // When
        coEvery { mockUrlShortenerRepository.createAlias(originalUrl) } returns alias


        //Then
        val result = mockUrlShortenerRepository.createAlias(originalUrl)
        assertEquals(alias.links!!.self, result.links!!.self)
    }
}