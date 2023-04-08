package com.nubank.urlshortener.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nubank.urlshortener.data.model.Alias
import com.nubank.urlshortener.databinding.ItemAliasBinding


class AliasAdapter() :
    ListAdapter<Alias, AliasAdapter.AliasViewHolder>(AliasDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AliasViewHolder {
        val binding = ItemAliasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AliasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AliasViewHolder, position: Int) {
        val alias = getItem(position)
        holder.bind(alias)
    }

    inner class AliasViewHolder(private val binding: ItemAliasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(alias: Alias) {
            binding.apply {
                tvAlias.text = alias.alias ?: "Error retrieving data from model"
                tvOriginalUrl.text = alias.links?.self ?: "Error retrieving data from model"
            }
        }
    }

    // This is a workaround to avoid a bug in the ListAdapter
    override fun submitList(list: List<Alias>?) {
        super.submitList(list?.toList())
    }
}

object AliasDiffCallback : DiffUtil.ItemCallback<Alias>() {
    override fun areItemsTheSame(oldItem: Alias, newItem: Alias): Boolean {
        return oldItem.alias == newItem.alias
    }

    override fun areContentsTheSame(oldItem: Alias, newItem: Alias): Boolean {
        return oldItem == newItem
    }
}