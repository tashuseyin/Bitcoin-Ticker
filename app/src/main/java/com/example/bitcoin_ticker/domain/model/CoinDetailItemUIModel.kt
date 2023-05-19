package com.example.bitcoin_ticker.domain.model

import com.example.bitcoin_ticker.data.remote.model.Description
import com.example.bitcoin_ticker.data.remote.model.Image
import com.example.bitcoin_ticker.data.remote.model.MarketData

data class CoinDetailItemUIModel(
    val id: String,
    val name: String,
    val symbol: String,
    val description: Description,
    val hashingAlgorithm: String?,
    val image: Image,
    val marketData: MarketData,
    val lastUpdated: String,
    val isFavorite: Boolean = false
)