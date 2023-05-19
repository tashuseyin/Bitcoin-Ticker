package com.example.bitcoin_ticker.presentation.coin_detail

sealed class CoinDetailUIEvent {
    data class LoadCoinDetail(val coinId: String): CoinDetailUIEvent()
}