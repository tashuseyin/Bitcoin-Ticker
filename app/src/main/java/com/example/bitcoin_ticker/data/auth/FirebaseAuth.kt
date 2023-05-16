package com.example.bitcoin_ticker.data.auth

import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.domain.model.LoginResult
import com.example.bitcoin_ticker.domain.model.RegisterResult
import com.example.bitcoin_ticker.domain.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuth : AuthenticationProxy {
    override suspend fun register(email: String, password: String): RegisterResult {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password)
            RegisterResult(true)
        } catch (e: Exception) {
            RegisterResult(false)
        }
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password)
            LoginResult(true)
        } catch (e: Exception) {
            LoginResult(false)
        }
    }

    override fun getCurrentUser(): User {
        val firebaseUser = Firebase.auth.currentUser
        return User(firebaseUser?.uid ?: "", firebaseUser?.email ?: "")
    }
}