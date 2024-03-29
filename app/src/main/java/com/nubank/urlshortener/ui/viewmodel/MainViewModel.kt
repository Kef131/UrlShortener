package com.nubank.urlshortener.ui.viewmodel

import androidx.lifecycle.*
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.repository.UrlShortenerRepositoryImpl
import kotlinx.coroutines.launch


class MainViewModel constructor(private val repository: UrlShortenerRepositoryImpl): ViewModel() {

    private val _aliases = MutableLiveData<List<Alias>>()
    val aliases: LiveData<List<Alias>> get() = _aliases

    val isLoading = MutableLiveData<Boolean>()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        _aliases.value = mutableListOf()
    }
    fun createAlias(url: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val alias = repository.createAlias(url)
                val updatedAliases = mutableListOf<Alias>().apply {
                    addAll(aliases.value ?: emptyList())
                    add(alias)
                    isLoading.postValue(false)
                }
                _aliases.value = updatedAliases
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}

class MainViewModelFactory constructor(private val repository: UrlShortenerRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
             MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}