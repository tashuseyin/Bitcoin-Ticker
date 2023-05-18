package com.example.bitcoin_ticker.domain.use_case.login

import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationProxy: AuthenticationProxy) {

    operator fun invoke(email: String, password: String) =
        authenticationProxy.login(email, password)

}