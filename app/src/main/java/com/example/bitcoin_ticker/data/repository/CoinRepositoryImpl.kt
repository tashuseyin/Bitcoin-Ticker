package com.example.bitcoin_ticker.data.repository

import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem
import com.example.bitcoin_ticker.domain.datasource.RemoteDataSource
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CoinRepository {

    override suspend fun getAllCoins(): List<CoinListItem> {
        return remoteDataSource.getAllCoins()
    }

    override suspend fun getCoinByID(id: String): List<CoinDetailItem> {
        return remoteDataSource.getCoinByID(id)
    }
}