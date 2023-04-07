package com.nubank.urlshortener.data.remote

import com.nubank.urlshortener.data.model.Alias
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UrlShortenerApi {


    @POST("api/alias")
    fun createAlias(@Body urlData: Map<String, String>): Call<Alias>

    companion object {
        private const val BASE_URL = "https://url-shortener-server.onrender.com/"

        fun create(): UrlShortenerApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(UrlShortenerApi::class.java)
        }
    }
}