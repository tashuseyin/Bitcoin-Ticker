package com.example.bitcoin_ticker.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCoins(coinList: List<CoinListItemEntity>)

    @Query("SELECT * FROM coin_entity_list WHERE name LIKE :query OR symbol LIKE :query")
    suspend fun getSearchCoinList(query: String): List<CoinListItemEntity>

    @Query("SELECT * FROM coin_entity_list")
    suspend fun getAllDatabaseCoins(): List<CoinListItemEntity>

}