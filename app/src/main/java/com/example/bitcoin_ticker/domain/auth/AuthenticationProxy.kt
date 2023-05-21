package com.example.bitcoin_ticker.domain.auth

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.data.auth.enums.LoginResult
import com.example.bitcoin_ticker.data.auth.enums.RegisterResult
import com.example.bitcoin_ticker.data.auth.model.AuthResponse
import com.example.bitcoin_ticker.data.auth.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationProxy {
    fun register(email: String, password: String): Flow<Resource<AuthResponse>>

    fun login(email: String, password: String): Flow<Resource<AuthResponse>>

    fun getCurrentUser(): User?

    suspend fun logout()
}