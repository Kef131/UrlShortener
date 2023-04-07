package com.nubank.urlshortener.ui.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.remote.UrlShortenerApi
import com.nubank.urlshortener.data.repository.UrlShortenerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UrlShortenerViewModel() : ViewModel() {
    private val urlShortenerApi = UrlShortenerApi.create()

    val aliasLiveData = MutableLiveData<Alias?>()

    fun createAlias(url: String) {
        val urlData = mapOf("url" to url)

        viewModelScope.launch(Dispatchers.IO) {
            urlShortenerApi.createAlias(urlData).enqueue(object : Callback<Alias> {
                override fun onResponse(call: Call<Alias>, response: Response<Alias>) {
                    if (response.isSuccessful) {
                        aliasLiveData.postValue(response.body())
                    } else {
                        Log.e("CALLERROR", response.errorBody().toString())
                        aliasLiveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Alias>, t: Throwable) {
                    aliasLiveData.postValue(null)
                }
            })

        }
    }
}
