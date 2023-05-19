package com.example.bitcoin_ticker.domain.use_case.coins

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
        emit(Resource.Loading())
        try {
            emit(Resource.Success(coinRepository.getAllCoins().toDomain()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}