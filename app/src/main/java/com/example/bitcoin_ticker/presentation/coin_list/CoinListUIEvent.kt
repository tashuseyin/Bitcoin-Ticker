package com.example.bitcoin_ticker.presentation.coin_list

sealed class CoinListUIEvent {

    object LoadCoins: CoinListUIEvent()

    data class LoadSearchCoinList(val query: String): CoinListUIEvent()
}