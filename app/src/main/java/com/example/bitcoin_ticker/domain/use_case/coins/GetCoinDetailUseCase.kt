package com.example.bitcoin_ticker.domain.use_case.coins

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.mapper.toDomain
import com.example.bitcoin_ticker.domain.model.CoinDetailItemUIModel
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetailItemUIModel>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = coinRepository.getCoinByID(coinId).toDomain()
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        }
    }
}