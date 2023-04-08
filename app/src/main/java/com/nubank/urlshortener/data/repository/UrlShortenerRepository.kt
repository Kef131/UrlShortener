package com.nubank.urlshortener.data.repository

import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.remote.UrlShortenerApi
import javax.inject.Inject

class UrlShortenerRepository @Inject constructor(private val api: UrlShortenerApi) {

    suspend fun createAlias(url: String): Alias {
        val urlData = mapOf("url" to url)
        val response = api.createAlias(urlData)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Unable to shorten URL")
        } else {
            throw Exception("Error: ${response.code()}")
        }
    }

}