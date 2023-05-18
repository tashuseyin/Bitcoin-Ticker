package com.example.bitcoin_ticker.presentation.login

import com.example.bitcoin_ticker.presentation.register.RegisterUIEvent


sealed class LoginUIEvent {
    object Login : LoginUIEvent()
    data class EnteringEmail(val email: String) : LoginUIEvent()
    data class EnteringPassword(val password: String) : LoginUIEvent()
}