package com.example.bitcoin_ticker.data.repository

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity
import com.example.bitcoin_ticker.data.remote.model.CoinDetailItem
import com.example.bitcoin_ticker.data.remote.model.CoinListItem
import com.example.bitcoin_ticker.domain.datasource.local.LocalDataSource
import com.example.bitcoin_ticker.domain.datasource.remote.RemoteDataSource
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration

class CoinRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CoinRepository {

    override suspend fun getAllCoins(): List<CoinListItem> {
        return remoteDataSource.getAllCoins()
    }

    override suspend fun getCoinByID(id: String): CoinDetailItem {
        return remoteDataSource.getCoinByID(id)
    }

    override suspend fun insertAllCoins(coinList: List<CoinListItemEntity>) {
        return localDataSource.insertAllCoins(coinList)
    }

    override fun getSearchCoinList(query: String): Flow<List<CoinListItemEntity>> {
        return localDataSource.getSearchCoinList("%$query%")
    }

    override fun getAllDatabaseCoins(): Flow<List<CoinListItemEntity>> {
        return localDataSource.getAllDatabaseCoins()
    }

    override fun updateCoinPrice(period: Duration, coinId: String): Flow<Resource<Double>> = flow {
        emit(Resource.Loading())
        try {
            val data = remoteDataSource.getCoinByID(coinId)
            data.marketData.currentPrice.let {
                emit(Resource.Success(it.usd))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }
}