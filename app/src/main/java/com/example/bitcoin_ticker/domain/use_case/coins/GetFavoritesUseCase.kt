package com.example.bitcoin_ticker.domain.use_case.coins

import com.example.bitcoin_ticker.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke() =
        firebaseRepository.getFavoriteCoins()
}