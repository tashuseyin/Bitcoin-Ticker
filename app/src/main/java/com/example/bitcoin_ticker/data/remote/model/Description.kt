package com.example.bitcoin_ticker.data.remote.model

import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en")
    val en: String,
)
