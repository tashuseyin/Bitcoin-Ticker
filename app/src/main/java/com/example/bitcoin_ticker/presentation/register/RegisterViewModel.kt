package com.example.bitcoin_ticker.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.core.validation.EmailValidation
import com.example.bitcoin_ticker.core.validation.PasswordRule
import com.example.bitcoin_ticker.core.validation.PasswordValidation
import com.example.bitcoin_ticker.domain.use_case.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val emailValidation = EmailValidation()
    private val passwordValidation = PasswordValidation()

    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState

    private val _registerButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val registerButtonEnabled: LiveData<Boolean> = _registerButtonEnabled

    private val _passwordRules: MutableLiveData<List<PasswordRule>> = MutableLiveData(passwordValidation.rules)
    val passwordRules: LiveData<List<PasswordRule>> = _passwordRules


    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.EnteringEmail -> {
                enteringEmail(event.email)
            }
            is RegisterUIEvent.EnteringPassword -> {
                enteringPassword(event.password)
            }
            is RegisterUIEvent.Register -> {
                register()
            }
        }
    }

    private fun checkRegisterButtonEnabled() {
        _registerButtonEnabled.value =
            emailValidation.isValidEmail(_uiState.value.email) && passwordValidation.getRules(
                _uiState.value.password
            )
    }

    private fun enteringEmail(email: String) {
        _uiState.update { it.copy(email = email) }
        checkRegisterButtonEnabled()
    }

    private fun enteringPassword(password: String) {
        _uiState.update { it.copy(password = password) }
         passwordValidation.getRules(password)
        _passwordRules.value = passwordValidation.rules
        checkRegisterButtonEnabled()
    }

    private fun register() {
        viewModelScope.launch {
            registerUseCase(_uiState.value.email, _uiState.value.password).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(registerResult = result.data, isLoading = false) }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "An unexcepted error occurred",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}