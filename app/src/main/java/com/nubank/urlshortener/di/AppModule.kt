//package com.nubank.urlshortener.di
//
//import com.nubank.urlshortener.data.remote.UrlShortenerApi
//import com.nubank.urlshortener.data.repository.UrlShortenerRepository
//import com.nubank.urlshortener.data.repository.UrlShortenerRepositoryImpl
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object AppModule {
//
//    @Module
//    @InstallIn(SingletonComponent::class)
//    object AppModule {
//
//        @Provides
//        fun provideUrlShortenerApi(): UrlShortenerApi {
//            return Retrofit.Builder()
//                .baseUrl("https://url-shortener-server.onrender.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(UrlShortenerApi::class.java)
//        }
//
//        @Provides
//        fun provideUrlShortenerRepository(api: UrlShortenerApi): UrlShortenerRepository {
//            return UrlShortenerRepositoryImpl(api)
//        }
//    }
//}