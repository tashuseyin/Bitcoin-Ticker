package com.example.bitcoin_ticker.presentation.coin_list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoin_ticker.databinding.CoinListItemBinding
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel

class CoinListViewHolder(private val rowCoinItemBinding: CoinListItemBinding) :
    RecyclerView.ViewHolder(rowCoinItemBinding.root) {

    fun bind(coinListItem: CoinListItemUIModel, onItemClick: ((String) -> Unit)?) {
        rowCoinItemBinding.apply {
            coinName.text = coinListItem.name
            coinSymbol.text = coinListItem.symbol

            root.setOnClickListener {
                onItemClick?.invoke(coinListItem.id)
            }
        }
    }
}
