package com.example.bitcoin_ticker.domain.repository

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel
import com.example.bitcoin_ticker.domain.model.FavoriteCoin
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    fun addFavoriteCoin(favoriteCoin: FavoriteCoin): Flow<Resource<Task<Void>>>
    fun getFavoriteCoins(): Flow<Resource<List<FavoriteCoin>>>
    fun removeFavoriteCoin(id: String): Flow<Resource<Task<Void>>>
    fun checkCoinFavorite(id: String): Flow<Resource<Boolean>>
}