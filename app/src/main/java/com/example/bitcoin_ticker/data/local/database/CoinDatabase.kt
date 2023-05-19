package com.example.bitcoin_ticker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity


@Database(
    entities = [CoinListItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}