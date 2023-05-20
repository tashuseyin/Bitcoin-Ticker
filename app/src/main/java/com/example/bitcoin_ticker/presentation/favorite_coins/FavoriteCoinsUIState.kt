package com.example.bitcoin_ticker.presentation.favorite_coins

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.domain.model.FavoriteCoin

data class FavoriteCoinsUIState(
    val isLoading: Boolean = false,
    val coins: List<FavoriteCoin> = emptyList(),
    val isShowErrorLayout: Boolean = false,
    val error: String = Constant.EMPTY_STRING,
)