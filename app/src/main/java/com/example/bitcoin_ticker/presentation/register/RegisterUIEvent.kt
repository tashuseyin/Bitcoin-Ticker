package com.example.bitcoin_ticker.presentation.register

sealed class RegisterUIEvent {

    data class Register(val email: String, val password: String) : RegisterUIEvent()
    data class EnteringEmail(val email: String) : RegisterUIEvent()
    data class EnteringPassword(val password: String) : RegisterUIEvent()
}
