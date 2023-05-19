package com.example.bitcoin_ticker.domain.mapper

import com.example.bitcoin_ticker.data.local.entity.CoinListItemEntity
import com.example.bitcoin_ticker.data.remote.model.*
import com.example.bitcoin_ticker.domain.model.CoinDetailItemUIModel
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel
import com.example.bitcoin_ticker.domain.model.FavoriteCoin

fun List<CoinListItem>.toDomain(): List<CoinListItemUIModel> {
    return if (this.isEmpty()) {
        emptyList()
    } else {
        this.map {
            CoinListItemUIModel(
                id = it.id,
                name = it.name,
                symbol = it.symbol
            )
        }
    }
}

fun List<CoinListItemUIModel>.toEntity(): List<CoinListItemEntity> {
    return if (this.isEmpty()) {
        emptyList()
    } else {
        this.map {
            CoinListItemEntity(
                uid = it.id,
                name = it.name,
                symbol = it.symbol
            )
        }
    }
}

fun List<CoinListItemEntity>.toUIModel(): List<CoinListItemUIModel> {
    return if (this.isEmpty()) {
        emptyList()
    } else {
        this.map {
            CoinListItemUIModel(
                id = it.uid,
                name = it.name,
                symbol = it.symbol
            )
        }
    }
}

fun CoinDetailItem.toDomain(): CoinDetailItemUIModel {
    return CoinDetailItemUIModel(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        description = this.description,
        hashingAlgorithm = this.hashingAlgorithm,
        image = this.image,
        marketData = this.marketData,
        lastUpdated = this.lastUpdated,
    )
}

fun CoinDetailItemUIModel?.toFavoriteCoin(): FavoriteCoin {
    return FavoriteCoin(
        id = this?.id,
        name = this?.name,
        symbol = this?.symbol,
        isFavorite = this?.isFavorite ?: false
    )
}