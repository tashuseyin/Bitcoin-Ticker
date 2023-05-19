package com.example.bitcoin_ticker.domain.use_case.coins

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.mapper.toUIModel
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchCoinListUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(query: String): Flow<Resource<List<CoinListItemUIModel>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(coinRepository.getSearchCoinList(query).toUIModel()))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}