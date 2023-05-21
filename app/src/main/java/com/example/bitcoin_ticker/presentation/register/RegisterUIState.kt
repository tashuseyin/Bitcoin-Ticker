package com.example.bitcoin_ticker.presentation.register

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.data.auth.enums.RegisterResult
import com.example.bitcoin_ticker.data.auth.model.AuthResponse

data class RegisterUIState(
    val isLoading: Boolean = false,
    val authResponse: AuthResponse? = null,
    val error: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING
)