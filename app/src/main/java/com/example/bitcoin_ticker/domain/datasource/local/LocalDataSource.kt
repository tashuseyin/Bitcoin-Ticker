package com.example.bitcoin_ticker.domain.datasource.local


import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertAllCoins(coinList: List<CoinListItemEntity>)

    fun getSearchCoinList(query: String): Flow<List<CoinListItemEntity>>

    fun getAllDatabaseCoins(): Flow<List<CoinListItemEntity>>
}