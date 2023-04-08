package com.nubank.urlshortener.data.remote

import com.nubank.urlshortener.data.model.Alias
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface  UrlShortenerApi {

    @POST("api/alias")
    suspend fun createAlias(@Body urlData: Map<String, String>): Response<Alias>

}