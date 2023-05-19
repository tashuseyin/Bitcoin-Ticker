package com.example.bitcoin_ticker.data.remote.model

import com.google.gson.annotations.SerializedName

data class CoinDetailItem(
    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: String,
    val id: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("market_data")
    val marketData: MarketData,
    val name: String,
    val symbol: String
)