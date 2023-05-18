package com.example.bitcoin_ticker.presentation.register

sealed class RegisterUIEvent {

    object Register : RegisterUIEvent()
    data class EnteringEmail(val email: String) : RegisterUIEvent()
    data class EnteringPassword(val password: String) : RegisterUIEvent()
}
