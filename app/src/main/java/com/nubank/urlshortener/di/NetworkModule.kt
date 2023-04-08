package com.nubank.urlshortener.di

import com.nubank.urlshortener.data.remote.UrlShortenerApi
import com.nubank.urlshortener.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesUrlShortenerApi(retrofit: Retrofit): UrlShortenerApi {
        return retrofit.create(UrlShortenerApi::class.java)
    }
}