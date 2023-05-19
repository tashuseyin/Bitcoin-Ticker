package com.example.bitcoin_ticker.domain.use_case.coins

import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import javax.inject.Inject

class InsertAllCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    suspend operator fun invoke(coinList:List<CoinListItemEntity>) = coinRepository.insertAllCoins(coinList)
}