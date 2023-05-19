package com.example.bitcoin_ticker.presentation.coin_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bitcoin_ticker.databinding.CoinListItemBinding
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel

class CoinListAdapter : ListAdapter<CoinListItemUIModel, CoinListViewHolder>(CoinItemDiffCallback()) {
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(
            CoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClick)
        }
    }
}

class CoinItemDiffCallback : DiffUtil.ItemCallback<CoinListItemUIModel>() {
    override fun areItemsTheSame(oldItem: CoinListItemUIModel, newItem: CoinListItemUIModel) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: CoinListItemUIModel, newItem: CoinListItemUIModel) =
        oldItem == newItem
}