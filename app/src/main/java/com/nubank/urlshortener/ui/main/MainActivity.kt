package com.nubank.urlshortener.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.repository.UrlShortenerRepositoryImpl
import com.nubank.urlshortener.databinding.ActivityMainBinding
import com.nubank.urlshortener.ui.adapter.AliasAdapter
import com.nubank.urlshortener.ui.viewmodel.MainViewModel
import com.nubank.urlshortener.ui.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var aliasAdapter: AliasAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(UrlShortenerRepositoryImpl()))[MainViewModel::class.java]

        initRecyclerView()
        aliasAdapter = AliasAdapter()
        setupClickListeners()
        observeViewModel()
    }
    //PARTE 2-3
    //when click the item opens browser
    // cuanod se le da click eliminar los items
    // opc dragger  para eliminar o boton  para eliminar

    private fun observeViewModel() {
        mainViewModel.aliases.observe(this) { aliases ->
            aliasAdapter.submitList(aliases)
//            binding.tvLastAlias.text = aliases.lastOrNull()?.alias ?: "No aliases created yet"
//            binding.tvLastUrl.text = aliases.lastOrNull()?.links?.self ?: "No aliases created yet"
        }

        mainViewModel.isLoading.observe(this) {
            binding.pbLoading.isVisible = it
        }

        mainViewModel.errorMessage.observe(this) {
            showToast(it)
        }
    }

    private fun setupClickListeners() {
        binding.btnSendUrl.setOnClickListener {
            val url = binding.etInputUrl.text.toString().trim()
            if (url.isNotEmpty() && isValidUrl(url)) {
                mainViewModel.createAlias(url)
                binding.etInputUrl.text.clear()
                showToast("Alias created successfully")
            } else {
                showToast("Please enter a valid URL")
                binding.etInputUrl.setText("")
            }
        }
    }

    private fun initRecyclerView() {
        aliasAdapter = AliasAdapter()
        binding.rvListAliases.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = aliasAdapter
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun isValidUrl(url: String): Boolean {
        val urlRegex = "^(https?://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$".toRegex()
        return urlRegex.matches(url)
    }

    //create dialog
    fun createDialog(alias: Alias) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Alias Information")
            .setMessage("Alias: ${alias.alias}\nOriginal URL: ${alias.links?.self}")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
    }

}
