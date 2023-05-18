package com.example.bitcoin_ticker.domain.auth

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.model.LoginResult
import com.example.bitcoin_ticker.domain.model.RegisterResult
import com.example.bitcoin_ticker.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationProxy {
    fun register(email: String, password: String): Flow<Resource<RegisterResult>>

    fun login(email: String, password: String): Flow<Resource<LoginResult>>

    fun getCurrentUser(): User?
}