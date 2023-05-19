package com.example.bitcoin_ticker.presentation.favorite_coins.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoin_ticker.databinding.CoinListItemBinding
import com.example.bitcoin_ticker.domain.model.FavoriteCoin

class FavoriteCoinsViewHolder(private val rowCoinItemBinding: CoinListItemBinding) :
    RecyclerView.ViewHolder(rowCoinItemBinding.root) {

    fun bind(favoriteCoin: FavoriteCoin, onItemClick: ((String?) -> Unit)?) {
        rowCoinItemBinding.apply {
            coinName.text = favoriteCoin.name
            coinSymbol.text = favoriteCoin.symbol

            root.setOnClickListener {
                onItemClick?.invoke(favoriteCoin.id)
            }
        }
    }
}
