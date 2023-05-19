package com.example.bitcoin_ticker.domain.datasource.local


import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity

interface LocalDataSource {

    suspend fun insertAllCoins(coinList: List<CoinListItemEntity>)

    suspend fun getSearchCoinList(query: String): List<CoinListItemEntity>

    suspend fun getAllDatabaseCoins(): List<CoinListItemEntity>
}