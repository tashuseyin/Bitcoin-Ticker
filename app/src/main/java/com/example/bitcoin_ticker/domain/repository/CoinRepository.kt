package com.example.bitcoin_ticker.domain.repository

import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem

interface CoinRepository {
    suspend fun getAllCoins(): List<CoinListItem>

    suspend fun getCoinByID(id: String): List<CoinDetailItem>
}