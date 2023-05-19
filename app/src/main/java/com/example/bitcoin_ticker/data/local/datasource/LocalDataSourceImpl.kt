package com.example.bitcoin_ticker.data.local.datasource

import com.example.bitcoin_ticker.data.local.database.CoinDao
import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity
import com.example.bitcoin_ticker.domain.datasource.local.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val coinDao: CoinDao): LocalDataSource {

    override suspend fun insertAllCoins(coinList: List<CoinListItemEntity>) {
        return coinDao.insertAllCoins(coinList)
    }

    override suspend fun getSearchCoinList(query: String): List<CoinListItemEntity> {
        return coinDao.getSearchCoinList(query)
    }

    override suspend fun getAllDatabaseCoins(): List<CoinListItemEntity> {
        return coinDao.getAllDatabaseCoins()
    }
}