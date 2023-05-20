package com.example.bitcoin_ticker.domain.repository

import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity
import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getAllCoins(): List<CoinListItem>

    suspend fun getCoinByID(id: String): CoinDetailItem

    suspend fun insertAllCoins(coinList: List<CoinListItemEntity>)

    fun getSearchCoinList(query: String): Flow<List<CoinListItemEntity>>

    fun getAllDatabaseCoins(): Flow<List<CoinListItemEntity>>
}