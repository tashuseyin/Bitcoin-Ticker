package com.example.bitcoin_ticker.domain.use_case.coins


import com.example.bitcoin_ticker.domain.model.FavoriteCoin
import com.example.bitcoin_ticker.domain.repository.FirebaseRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke(favoriteCoin: FavoriteCoin) =
        firebaseRepository.addFavoriteCoin(favoriteCoin)
}