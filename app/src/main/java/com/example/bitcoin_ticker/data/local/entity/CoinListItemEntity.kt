package com.example.bitcoin_ticker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_entity_list")
data class CoinListItemEntity(
    @PrimaryKey
    val uid: String,
    val name: String,
    val symbol: String,
    val isFavorite: Boolean = false
)