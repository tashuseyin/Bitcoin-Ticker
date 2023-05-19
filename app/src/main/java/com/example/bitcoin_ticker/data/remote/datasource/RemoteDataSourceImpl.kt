package com.example.bitcoin_ticker.data.remote.datasource

import com.example.bitcoin_ticker.data.remote.CoinApiService
import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem
import com.example.bitcoin_ticker.domain.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val coinApiService: CoinApiService
) : RemoteDataSource {

    override suspend fun getAllCoins(): List<CoinListItem> {
        return coinApiService.getAllCoins()
    }

    override suspend fun getCoinByID(id: String): List<CoinDetailItem> {
        return coinApiService.getCoinByID(id)
    }
}