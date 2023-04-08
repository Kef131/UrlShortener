package com.nubank.urlshortener.ui.viewmodel

import androidx.lifecycle.*
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.repository.UrlShortenerRepository
import kotlinx.coroutines.launch


class MainViewModel constructor(private val repository: UrlShortenerRepository): ViewModel() {

    // LiveData properties to expose the data to the UI layer
    private val _aliases = MutableLiveData<List<Alias>>()
    val aliases: LiveData<List<Alias>> get() = _aliases

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        _aliases.value = mutableListOf()
    }
    fun createAlias(url: String) {
        viewModelScope.launch {
            try {
                val alias = repository.createAlias(url)
                val updatedAliases = mutableListOf<Alias>().apply {
                    addAll(aliases.value ?: emptyList())
                    add(alias)
                }
                _aliases.value = updatedAliases
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}

class MainViewModelFactory constructor(private val repository: UrlShortenerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
             MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}