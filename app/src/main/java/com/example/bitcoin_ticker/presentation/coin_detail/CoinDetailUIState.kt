package com.example.bitcoin_ticker.presentation.coin_detail

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.domain.model.CoinDetailItemUIModel

data class CoinDetailUIState(
    val isLoading: Boolean = false,
    val coin: CoinDetailItemUIModel? = null,
    val error: String = Constant.EMPTY_STRING,
)