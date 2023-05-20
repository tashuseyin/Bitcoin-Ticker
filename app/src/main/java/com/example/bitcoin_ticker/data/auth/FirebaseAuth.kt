package com.example.bitcoin_ticker.data.auth

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.data.auth.model.LoginResult
import com.example.bitcoin_ticker.data.auth.model.RegisterResult
import com.example.bitcoin_ticker.data.auth.model.User
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuth : AuthenticationProxy {
    override fun register(email: String, password: String): Flow<Resource<RegisterResult>> =
        flow {
            emit(Resource.Loading())
            try {
                Firebase.auth.createUserWithEmailAndPassword(email, password).await()
                emit(Resource.Success(RegisterResult.SUCCESS_REGISTER))
            } catch (e: FirebaseAuthUserCollisionException) {
                emit(Resource.Error(RegisterResult.USER_ALREADY_EXISTS.name))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                emit(Resource.Error(RegisterResult.FAILURE_REGISTER.name))
            }
        }

    override fun login(email: String, password: String): Flow<Resource<LoginResult>> =
        flow {
            emit(Resource.Loading())
            try {
                Firebase.auth.signInWithEmailAndPassword(email, password).await()
                emit(Resource.Success(LoginResult.SUCCESS_LOGIN))
            } catch (e: FirebaseAuthInvalidUserException) {
                emit(Resource.Error(LoginResult.USER_NOT_FOUND.name))
            } catch (e: Exception) {
                emit(Resource.Error(LoginResult.LOGIN_FAILED.name))
            }
        }


    override fun getCurrentUser(): User? {
        val firebaseUser = Firebase.auth.currentUser ?: return null
        return User(firebaseUser.uid, firebaseUser.email ?: "")
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
    }
}