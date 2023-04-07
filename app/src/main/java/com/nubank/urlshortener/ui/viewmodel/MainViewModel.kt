package com.nubank.urlshortener.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.repository.UrlShortenerRepository
import kotlinx.coroutines.launch


class MainViewModel(private val repository: UrlShortenerRepository): ViewModel() {

    // LiveData properties to expose the data to the UI layer
    private val _recentAliases = MutableLiveData<List<Alias>>()
    val recentAliases: LiveData<List<Alias>> = _recentAliases

    private val _originalUrl = MutableLiveData<String>()
    val originalUrl: LiveData<String> = _originalUrl

    private val _createAliasResult = MutableLiveData<Result<Alias>>()
    val createAliasResult: LiveData<Result<Alias>> = _createAliasResult

    private val _getOriginalUrlResult = MutableLiveData<Result<String>>()
    val getOriginalUrlResult: LiveData<Result<String>> = _getOriginalUrlResult

    /**
     * Creates an alias for the given URL and updates the recent aliases list if successful.
     *
     * @param url The URL to shorten.
     */
    fun createAlias(url: String) {
        viewModelScope.launch {
            repository.createAlias(url).collect { result ->
                _createAliasResult.value = result
                result.onSuccess { alias ->
                    updateRecentAliases()
                }
            }
        }
    }

    /**
     * Retrieves the original URL associated with the given alias.
     *
     * @param alias The alias to look up.
     */
    fun gerOriginalUrl(alias: String) {
        viewModelScope.launch {
            repository.getOriginalUrl(alias).collect { result ->
                _getOriginalUrlResult.value = result
                result.onSuccess { url ->
                    _originalUrl.value = url
                }
            }
        }
    }

    /**
     * Retrieves the list of recently shortened aliases.
     */
    private fun updateRecentAliases() {
        viewModelScope.launch {
            repository.getRecentAliases().collect { aliases ->
                _recentAliases.value = aliases
            }
        }
    }
}