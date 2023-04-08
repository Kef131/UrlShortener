//package com.nubank.urlshortener.ui.viewmodel
//
//import android.util.Log
//import androidx.lifecycle.*
//import com.nubank.urlshortener.data.model.Alias
//import com.nubank.urlshortener.data.remote.UrlShortenerApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class UrlShortenerViewModel() : ViewModel() {
//
//    val aliasLiveData = MutableLiveData<Alias?>()
//    val listAliasLiveData = MutableLiveData<List<Alias>>()
//
//    fun createAlias(url: String) {
//        val urlData = mapOf("url" to url)
//
//        viewModelScope.launch(Dispatchers.IO) {
//            UrlShortenerApi.instance().createAlias(urlData).enqueue(object : Callback<Alias> {
//                override fun onResponse(call: Call<Alias>, response: Response<Alias>) {
//                    if (response.isSuccessful) {
//                        aliasLiveData.postValue(response.body())
//                    } else {
//                        Log.e("CALLERROR", response.errorBody().toString())
//                        aliasLiveData.postValue(null)
//                    }
//                }
//
//                override fun onFailure(call: Call<Alias>, t: Throwable) {
//                    aliasLiveData.postValue(null)
//                }
//            })
//
//        }
//    }
//}
