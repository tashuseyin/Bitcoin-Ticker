package com.example.bitcoin_ticker.domain.use_case.register

import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authenticationProxy: AuthenticationProxy) {

    operator fun invoke(email: String, password: String) =
        authenticationProxy.register(email, password)
}