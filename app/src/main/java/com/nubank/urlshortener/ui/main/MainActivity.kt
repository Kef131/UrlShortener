package com.nubank.urlshortener.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nubank.urlshortener.data.remote.UrlShortenerApi
import com.nubank.urlshortener.databinding.ActivityMainBinding
import com.nubank.urlshortener.ui.adapter.AliasAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.data.repository.UrlShortenerRepository
import com.nubank.urlshortener.ui.viewmodel.MainViewModel
import com.nubank.urlshortener.ui.viewmodel.MainViewModelFactory
import org.chromium.net.UrlRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var aliasAdapter: AliasAdapter
    var aliases = mutableListOf<Alias>()

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(UrlShortenerRepository())).get(MainViewModel::class.java)

        initRecyclerView()


        mainViewModel.aliases.observe(this) { aliases ->
            aliasAdapter.submitList(aliases)
        }

        binding.btnSendUrl.setOnClickListener {
            val url = binding.etInputUrl.text.toString().trim()
            if (url.isNotEmpty()) {
                mainViewModel.createAlias(url)
                binding.etInputUrl.text.clear()
            }
        }

    }
    private fun initRecyclerView() {
        aliasAdapter = AliasAdapter(aliases)
        binding.rvListAliases.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = aliasAdapter
        }
    }

}
