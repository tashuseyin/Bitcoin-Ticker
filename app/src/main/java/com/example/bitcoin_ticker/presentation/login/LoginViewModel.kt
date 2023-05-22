package com.example.bitcoin_ticker.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.core.showSnackbar
import com.example.bitcoin_ticker.core.validation.EmailValidation
import com.example.bitcoin_ticker.domain.use_case.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidation: EmailValidation,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    private val _password: MutableLiveData<String> = MutableLiveData(Constant.EMPTY_STRING)
    val password: LiveData<String> = _password


    private val _email: MutableLiveData<String> = MutableLiveData(Constant.EMPTY_STRING)
    val email: LiveData<String> = _email

    private val _loginButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginButtonEnabled: LiveData<Boolean> = _loginButtonEnabled

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EnteringEmail -> {
                enteringEmail(event.email)
            }
            is LoginUIEvent.EnteringPassword -> {
                enteringPassword(event.password)
            }
            is LoginUIEvent.Login -> {
                login()
            }
        }
    }

    private fun checkLoginButtonEnabled() {
        _loginButtonEnabled.value = emailValidation.isValidEmail(_email.value.toString())
    }

    private fun enteringEmail(email: String) {
        _email.value = email
        checkLoginButtonEnabled()
    }

    private fun enteringPassword(password: String) {
        _password.value = password
    }


    private fun login() {
        viewModelScope.launch {
            loginUseCase.invoke(_email.value.toString(), _password.value.toString())
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _uiState.value = LoginUIState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _uiState.value = LoginUIState(authResponse = result.data)
                        }
                        is Resource.Error -> {
                            _uiState.value = LoginUIState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
        }
    }
}