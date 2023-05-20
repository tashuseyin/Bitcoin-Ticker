package com.example.bitcoin_ticker.domain.use_case.coins

import com.example.bitcoin_ticker.domain.mapper.toUIModel
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchCoinListUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(query: String): Flow<List<CoinListItemUIModel>> = flow {
            coinRepository.getSearchCoinList(query).map { it.toUIModel() }
    }
}