package com.nubank.urlshortener.data.remote

import com.nubank.urlshortener.data.model.Alias
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UrlShortenerApi {

    @POST("api/alias")
    suspend fun createAlias(@Body urlData: Map<String, String>): Response<Alias>

    companion object {
        private const val BASE_URL = "https://url-shortener-server.onrender.com/"
        fun instance(): UrlShortenerApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(UrlShortenerApi::class.java)
        }
    }

}