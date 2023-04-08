package com.nubank.urlshortener.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nubank.urlshortener.data.repository.UrlShortenerRepository
import com.nubank.urlshortener.databinding.ActivityMainBinding
import com.nubank.urlshortener.ui.adapter.AliasAdapter
import com.nubank.urlshortener.ui.viewmodel.MainViewModel
import com.nubank.urlshortener.ui.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var aliasAdapter: AliasAdapter

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(UrlShortenerRepository())).get(MainViewModel::class.java)

        initRecyclerView()
        setupClickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.aliases.observe(this) { aliases ->
            aliasAdapter.submitList(aliases)
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
            if (url.isNotEmpty()) {
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

}
