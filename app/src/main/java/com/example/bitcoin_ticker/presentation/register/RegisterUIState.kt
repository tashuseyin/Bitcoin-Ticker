package com.example.bitcoin_ticker.presentation.register

import com.example.bitcoin_ticker.domain.model.LoginResult

data class RegisterUIState(
    val isLoading: Boolean = false,
    val loginResult: LoginResult? = null,
    val error: String = ""
)