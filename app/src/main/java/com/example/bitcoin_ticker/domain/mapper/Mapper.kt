package com.example.bitcoin_ticker.domain.mapper

import com.example.bitcoin_ticker.data.remote.model.CoinListItem
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel

fun List<CoinListItem>.toDomain(): List<CoinListItemUIModel> {
    return if (this.isEmpty()) {
        emptyList()
    } else {
        this.map {
            CoinListItemUIModel(
                id = it.id,
                name = it.name,
                symbol = it.symbol
            )
        }
    }
}