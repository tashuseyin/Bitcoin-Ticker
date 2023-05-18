package com.example.bitcoin_ticker.domain.model

import com.example.bitcoin_ticker.R

enum class LoginResult(val statusMessage: Int) {
    SUCCESS_LOGIN(R.string.success_login),
    WRONG_PASSWORD(R.string.wrong_password),
    USER_NOT_FOUND(R.string.user_not_found),
    LOGIN_FAILED(R.string.login_failed),
}
