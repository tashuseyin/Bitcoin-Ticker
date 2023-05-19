package com.example.bitcoin_ticker.data.remote.model

import com.google.gson.annotations.SerializedName

data class PriceChange24hInCurrency(
    @SerializedName("try")
    val tl: Double,
    @SerializedName("usd")
    val usd: Double
)