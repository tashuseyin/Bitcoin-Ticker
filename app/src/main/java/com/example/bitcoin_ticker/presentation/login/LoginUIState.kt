package com.example.bitcoin_ticker.presentation.login

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.data.auth.enums.LoginResult
import com.example.bitcoin_ticker.data.auth.model.AuthResponse

data class LoginUIState(
    val isLoading: Boolean = false,
    val authResponse: AuthResponse? = null,
    val error: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING
)