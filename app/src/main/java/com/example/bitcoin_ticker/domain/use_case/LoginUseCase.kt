package com.example.bitcoin_ticker.domain.use_case

import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.domain.model.LoginResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationProxy: AuthenticationProxy) {

    operator fun invoke(email: String, password: String): Flow<Resource<LoginResult>> =
        flow {
            try {
                val loginResult = authenticationProxy.login(email, password)
                emit(Resource.Success(loginResult))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
}