package com.nubank.urlshortener.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nubank.urlshortener.data.remote.UrlShortenerApi
import com.nubank.urlshortener.databinding.ActivityMainBinding
import com.nubank.urlshortener.ui.adapter.AliasAdapter
import com.nubank.urlshortener.ui.viewmodel.UrlShortenerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import com.nubank.urlshortener.data.model.Alias
import org.chromium.net.UrlRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var aliasAdapter: AliasAdapter
    var aliases = mutableListOf<Alias>()

    private val urlShortenerViewModel: UrlShortenerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()


        urlShortenerViewModel.aliasLiveData.observe(this, Observer { alias ->
            if (alias != null) {
                Toast.makeText(this, "Alias created succesfully", Toast.LENGTH_SHORT).show()
                aliases.add(alias)
                aliasAdapter.submitList(aliases)
            } else {
                Toast.makeText(this, "Error creating Alias", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnSendUrl.setOnClickListener {
            if (binding.etInputUrl.text.toString().isNotEmpty()) {
                urlShortenerViewModel.createAlias(binding.etInputUrl.text.toString())
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
