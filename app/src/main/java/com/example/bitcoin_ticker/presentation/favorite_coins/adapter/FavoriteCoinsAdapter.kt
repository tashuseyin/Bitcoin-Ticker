package com.example.bitcoin_ticker.presentation.favorite_coins.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bitcoin_ticker.databinding.CoinListItemBinding
import com.example.bitcoin_ticker.domain.model.FavoriteCoin

class FavoriteCoinsAdapter : ListAdapter<FavoriteCoin, FavoriteCoinsViewHolder>(CoinItemDiffCallback()) {
    var onItemClick: ((String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCoinsViewHolder {
        return FavoriteCoinsViewHolder(
            CoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteCoinsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClick)
        }
    }
}

class CoinItemDiffCallback : DiffUtil.ItemCallback<FavoriteCoin>() {
    override fun areItemsTheSame(oldItem: FavoriteCoin, newItem: FavoriteCoin) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: FavoriteCoin, newItem: FavoriteCoin) =
        oldItem == newItem
}