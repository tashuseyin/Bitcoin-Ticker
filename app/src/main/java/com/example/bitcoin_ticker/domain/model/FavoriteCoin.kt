package com.example.bitcoin_ticker.domain.model


data class FavoriteCoin(
    val id: String? = "",
    val name: String? = "",
    val symbol: String? = "",
    val isFavorite: Boolean = false
)