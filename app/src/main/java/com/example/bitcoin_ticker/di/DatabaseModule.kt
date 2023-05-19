package com.example.bitcoin_ticker.di

import android.content.Context
import androidx.room.Room
import com.example.bitcoin_ticker.data.local.database.CoinDao
import com.example.bitcoin_ticker.data.local.database.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCoinDatabase(@ApplicationContext appContext: Context): CoinDatabase =
        Room.databaseBuilder(
            appContext,
            CoinDatabase::class.java,
            "coins.db"
        ).build()

    @Provides
    @Singleton
    fun provideCoinDAO(coinDatabase: CoinDatabase): CoinDao =
        coinDatabase.coinDao()
}