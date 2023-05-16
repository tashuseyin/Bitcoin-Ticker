package com.example.bitcoin_ticker.domain.auth

import com.example.bitcoin_ticker.domain.model.LoginResult
import com.example.bitcoin_ticker.domain.model.RegisterResult
import com.example.bitcoin_ticker.domain.model.User

interface AuthenticationProxy {
    suspend fun register(email:String, password: String): RegisterResult

    suspend fun login(email: String, password: String): LoginResult

    fun getCurrentUser(): User?
}