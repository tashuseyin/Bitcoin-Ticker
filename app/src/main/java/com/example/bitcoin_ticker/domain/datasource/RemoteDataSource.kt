package com.example.bitcoin_ticker.domain.datasource

import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem

interface RemoteDataSource {
    suspend fun getAllCoins(): List<CoinListItem>

    suspend fun getCoinByID(id: String): List<CoinDetailItem>
}