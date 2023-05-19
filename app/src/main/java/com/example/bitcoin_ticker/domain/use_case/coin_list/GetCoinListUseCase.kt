package com.example.bitcoin_ticker.domain.use_case.coin_list

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.mapper.toDomain
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<CoinListItemUIModel>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = coinRepository.getAllCoins().toDomain()
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}