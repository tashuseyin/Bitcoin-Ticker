package com.example.bitcoin_ticker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val authenticationProxy: AuthenticationProxy
) : ViewModel() {

    private val _isCurrentUser: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isCurrentUser = _isCurrentUser.asStateFlow()

    fun onEvent(event: MainUIEvent) {
        when (event) {
            is MainUIEvent.Logout -> {
                logout()
            }
        }
    }

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        _isCurrentUser.value = authenticationProxy.getCurrentUser() != null
    }

    private fun logout() {
        viewModelScope.launch {
            authenticationProxy.logout()
        }
    }

}