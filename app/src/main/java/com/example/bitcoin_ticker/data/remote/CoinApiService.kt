package com.example.bitcoin_ticker.data.remote

import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApiService {

    @GET("coins/list")
    suspend fun getAllCoins(): List<CoinListItem>

    @GET("coins/{id}")
    suspend fun getCoinByID(@Path("id") id: String): CoinDetailItem
}