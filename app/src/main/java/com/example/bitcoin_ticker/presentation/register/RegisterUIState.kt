package com.example.bitcoin_ticker.presentation.register

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.core.validation.PasswordRule
import com.example.bitcoin_ticker.domain.model.LoginResult
import com.example.bitcoin_ticker.domain.model.RegisterResult

data class RegisterUIState(
    val isLoading: Boolean = false,
    val registerResult: RegisterResult? = null,
    val error: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING
)