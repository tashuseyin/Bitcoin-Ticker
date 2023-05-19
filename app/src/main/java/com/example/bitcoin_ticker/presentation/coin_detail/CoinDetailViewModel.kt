package com.example.bitcoin_ticker.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.use_case.coins.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinDetailUIState())
    val uiState: StateFlow<CoinDetailUIState> = _uiState

    init {
        savedStateHandle.get<String>(Constant.COIN_ID)?.let {
            onEvent(CoinDetailUIEvent.LoadCoinDetail(it))
        }
    }


    fun onEvent(event: CoinDetailUIEvent) {
        when (event) {
            is CoinDetailUIEvent.LoadCoinDetail -> {
                getCoinById(event.coinId)
            }
        }
    }

    private fun getCoinById(coinId: String) {
        viewModelScope.launch {
            getCoinDetailUseCase.invoke(coinId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(coin = result.data, isLoading = false) }
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