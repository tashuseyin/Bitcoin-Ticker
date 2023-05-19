package com.example.bitcoin_ticker.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.use_case.coin_list.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinListUseCase: GetCoinListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinListUIState())
    val uiState: StateFlow<CoinListUIState> = _uiState

    init {
        onEvent(CoinListUIEvent.LoadCoins)
    }

    fun onEvent(event: CoinListUIEvent) {
        when (event) {
            is CoinListUIEvent.LoadCoins -> {
                getCoins()
            }
        }
    }


    private fun getCoins() {
        viewModelScope.launch {
            getCoinListUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(coins = result.data ?: emptyList(), isLoading = false) }
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