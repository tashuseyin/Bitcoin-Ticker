package com.example.bitcoin_ticker.presentation.login

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.data.auth.model.LoginResult

data class LoginUIState(
    val isLoading: Boolean = false,
    val loginResult: LoginResult? = null,
    val error: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING
)