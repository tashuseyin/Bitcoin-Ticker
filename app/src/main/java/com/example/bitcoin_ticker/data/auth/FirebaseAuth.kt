package com.example.bitcoin_ticker.data.auth

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.data.auth.enums.LoginResult
import com.example.bitcoin_ticker.data.auth.enums.RegisterResult
import com.example.bitcoin_ticker.data.auth.model.AuthResponse
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
    override fun register(email: String, password: String): Flow<Resource<AuthResponse>> =
        flow {
            emit(Resource.Loading())
            try {
                Firebase.auth.createUserWithEmailAndPassword(email, password).await()
                emit(Resource.Success(AuthResponse(true,RegisterResult.SUCCESS_REGISTER.statusMessage)))
            } catch (e: FirebaseAuthUserCollisionException) {
                emit(Resource.Error(RegisterResult.USER_ALREADY_EXISTS.statusMessage.toString()))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                emit(Resource.Error(RegisterResult.FAILURE_REGISTER.statusMessage.toString()))
            }
        }

    override fun login(email: String, password: String): Flow<Resource<AuthResponse>> =
        flow {
            emit(Resource.Loading())
            try {
                Firebase.auth.signInWithEmailAndPassword(email, password).await()
                emit(Resource.Success(AuthResponse(true,LoginResult.SUCCESS_LOGIN.statusMessage)))
            } catch (e: FirebaseAuthInvalidUserException) {
                emit(Resource.Error(LoginResult.USER_NOT_FOUND.statusMessage.toString()))
            } catch (e: Exception) {
                emit(Resource.Error(LoginResult.LOGIN_FAILED.statusMessage.toString()))
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