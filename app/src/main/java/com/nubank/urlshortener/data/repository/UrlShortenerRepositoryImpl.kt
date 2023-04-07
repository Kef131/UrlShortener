//package com.nubank.urlshortener.data.repository
//
//import com.nubank.urlshortener.data.model.Alias
//import com.nubank.urlshortener.data.remote.UrlShortenerApi
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//
//// This class is responsible for fetching data from the API and storing it in memory.
//class UrlShortenerRepositoryImpl(private val api: UrlShortenerApi): UrlShortenerRepository {
//    // This is a simple in-memory cache for the recently created aliases.
//    private val recentAliases = mutableListOf<Alias>()
//
//    // This method is responsible for creating a shortened alias for the given URL and storing it in memory.
//    override fun createAlias(url: String): Flow<Result<Alias>> = flow {
//        val response = api.createAlias(mapOf("url" to url))
//        if (response.isSuccessful) {
//            response.body()?.let { alias ->
//                recentAliases.add(alias)
//                emit(Result.success(alias))
//            } ?: emit(Result.failure(Exception("Failed to create alias")))
//        } else {
//            emit(Result.failure(Exception("Failed to create alias")))
//        }
//    }
//
//    // This method is responsible for retrieving the original URL associated with the given alias.
//    override fun getOriginalUrl(alias: String): Flow<Result<String>> = flow {
//        val response = api.getOriginalUrl(alias)
//        if (response.isSuccessful) {
//            response.body()?.let { urlData ->
//                emit(Result.success(urlData["url"] ?: ""))
//            } ?: emit(Result.failure(Exception("Failed to get original URL")))
//        } else {
//            emit(Result.failure(Exception("Failed to get original URL")))
//        }
//    }
//
//    // This method is responsible for retrieving the list of recently shortened aliases.
//    override fun getRecentAliases(): Flow<List<Alias>> = flow {
//        emit(recentAliases.toList())
//    }
//}