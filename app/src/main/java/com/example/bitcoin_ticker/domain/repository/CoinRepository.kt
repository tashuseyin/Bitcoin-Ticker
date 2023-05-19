package com.example.bitcoin_ticker.domain.repository

import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity
import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem

interface CoinRepository {
    suspend fun getAllCoins(): List<CoinListItem>

    suspend fun getCoinByID(id: String): CoinDetailItem

    suspend fun insertAllCoins(coinList: List<CoinListItemEntity>)

    suspend fun getSearchCoinList(query: String): List<CoinListItemEntity>

    suspend fun getAllDatabaseCoins(): List<CoinListItemEntity>
}