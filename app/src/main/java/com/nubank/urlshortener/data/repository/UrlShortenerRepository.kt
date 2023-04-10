package com.nubank.urlshortener.data.repository

import com.nubank.urlshortener.data.model.Alias

interface UrlShortenerRepository {
    suspend fun createAlias(url: String): Alias
}