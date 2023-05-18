package com.example.bitcoin_ticker.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.core.validation.EmailValidation
import com.example.bitcoin_ticker.domain.use_case.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidation: EmailValidation,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

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
        _loginButtonEnabled.value = emailValidation.isValidEmail(_uiState.value.email)
    }

    private fun enteringEmail(email: String) {
        _uiState.update { it.copy(email = email) }
        checkLoginButtonEnabled()
    }

    private fun enteringPassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }


    private fun login() {
        viewModelScope.launch {
            loginUseCase.invoke(_uiState.value.email, _uiState.value.password).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(loginResult = result.data, isLoading = false) }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(error = result.message ?: "An unexcepted error occurred", isLoading = false)
                        }
                    }
                }
            }
        }
    }
}