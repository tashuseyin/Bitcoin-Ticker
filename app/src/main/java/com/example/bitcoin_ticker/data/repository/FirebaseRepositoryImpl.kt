package com.example.bitcoin_ticker.data.repository

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.core.Constant.COINS
import com.example.bitcoin_ticker.core.Constant.FAVORITES
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.domain.model.FavoriteCoin
import com.example.bitcoin_ticker.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val authenticationProxy: AuthenticationProxy,
    private val firebaseFireStore: FirebaseFirestore
) : FirebaseRepository {

    override fun addFavoriteCoin(favoriteCoin: FavoriteCoin): Flow<Resource<Task<Void>>> =
        flow {
            emit(Resource.Loading())
            try {
                authenticationProxy.getCurrentUser()?.id?.let {
                    val coin = firebaseFireStore.collection(FAVORITES).document(it)
                        .collection(COINS).document(favoriteCoin.id ?: Constant.EMPTY_STRING)
                        .set(favoriteCoin)
                    coin.await()
                    emit(Resource.Success(coin))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }

    override fun getFavoriteCoins(): Flow<Resource<List<FavoriteCoin>>> =
        flow {
            emit(Resource.Loading())
            try {
                authenticationProxy.getCurrentUser()?.id?.let {
                    val snapshot = firebaseFireStore.collection(FAVORITES).document(it)
                        .collection(COINS).get().await()

                    val data = snapshot.toObjects(FavoriteCoin::class.java)

                    emit(Resource.Success(data))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }


    override fun removeFavoriteCoin(id: String): Flow<Resource<Task<Void>>> = flow {
        emit(Resource.Loading())
        try {
            authenticationProxy.getCurrentUser()?.id?.let {
                val coinRef = firebaseFireStore.collection(FAVORITES).document(it)
                    .collection(COINS).document(id).delete()
                coinRef.await()

                emit(Resource.Success(coinRef))
            }
        } catch(e: Exception) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }

    override fun checkCoinFavorite(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            authenticationProxy.getCurrentUser()?.id?.let {
                val snapshot = firebaseFireStore.collection(FAVORITES).document(it)
                    .collection(COINS).get().await()

                val data = snapshot.toObjects(FavoriteCoin::class.java)

                data.let {
                    val coin = data.find { it.id == id }
                    if (coin == null)
                        emit(Resource.Success(false))
                    else
                        emit(Resource.Success(true))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}