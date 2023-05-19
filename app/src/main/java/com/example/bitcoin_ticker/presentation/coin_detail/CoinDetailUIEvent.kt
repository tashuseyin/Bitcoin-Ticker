package com.example.bitcoin_ticker.presentation.coin_detail

sealed class CoinDetailUIEvent {
    data class LoadCoinDetail(val coinId: String): CoinDetailUIEvent()
    data class CheckFavorite(val coinId: String): CoinDetailUIEvent()
    object OnClickFavoriteButton: CoinDetailUIEvent()

}