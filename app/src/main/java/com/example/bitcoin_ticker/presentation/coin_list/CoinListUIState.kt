package com.example.bitcoin_ticker.presentation.coin_list

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel

data class CoinListUIState(
    val isLoading: Boolean = false,
    val coins: List<CoinListItemUIModel> = emptyList(),
    val error: String = Constant.EMPTY_STRING,
)