package com.example.bitcoin_ticker.core.validation

import android.util.Patterns

class EmailValidation {

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}