package com.example.bitcoin_ticker.domain.use_case.coins

import com.example.bitcoin_ticker.domain.repository.FirebaseRepository
import javax.inject.Inject

class CheckFavoriteUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke(coinId: String) =
        firebaseRepository.checkCoinFavorite(coinId)
}