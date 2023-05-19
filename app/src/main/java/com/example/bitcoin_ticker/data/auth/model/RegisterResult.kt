package com.example.bitcoin_ticker.data.auth.model

import com.example.bitcoin_ticker.R

enum class RegisterResult(val statusMessage: Int) {
    SUCCESS_REGISTER(R.string.success_register),
    USER_ALREADY_EXISTS(R.string.user_already_exists),
    FAILURE_REGISTER(R.string.failure_register)
}
