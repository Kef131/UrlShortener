package com.nubank.urlshortener.data.repository

import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.remote.UrlShortenerApi

class UrlShortenerRepositoryImpl: UrlShortenerRepository {

    private val retrofit = UrlShortenerApi.instance()

    override suspend fun createAlias(url: String): Alias {
        val urlData = mapOf("url" to url)
        val response = retrofit.createAlias(urlData)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Unable to shorten URL")
        } else {
            throw Exception("Error: ${response.code()}")
        }
    }

}