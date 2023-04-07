package com.nubank.urlshortener.data.repository

import com.nubank.urlshortener.data.model.Alias
import kotlinx.coroutines.flow.Flow


interface UrlShortenerRepository {

    fun createAlias(url: String): Flow<Result<Alias>>

    fun getOriginalUrl(alias: String): Flow<Result<String>>

    fun getRecentAliases(): Flow<List<Alias>>
}