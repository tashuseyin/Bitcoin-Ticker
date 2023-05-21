package com.example.bitcoin_ticker.presentation.coin_detail

import androidx.lifecycle.*
import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.mapper.toFavoriteCoin
import com.example.bitcoin_ticker.domain.use_case.coins.AddFavoriteUseCase
import com.example.bitcoin_ticker.domain.use_case.coins.CheckFavoriteUseCase
import com.example.bitcoin_ticker.domain.use_case.coins.RemoveFavoriteUseCase
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
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinDetailUIState())
    val uiState: StateFlow<CoinDetailUIState> = _uiState

    init {
        savedStateHandle.get<String>(Constant.COIN_ID)?.let {
            onEvent(CoinDetailUIEvent.LoadCoinDetail(it))
            onEvent(CoinDetailUIEvent.CheckFavorite(it))
        }
    }


    fun onEvent(event: CoinDetailUIEvent) {
        when (event) {
            is CoinDetailUIEvent.LoadCoinDetail -> {
                getCoinById(event.coinId)
            }
            is CoinDetailUIEvent.OnClickFavoriteButton -> {
                favoriteButtonClick()
            }
            is CoinDetailUIEvent.CheckFavorite -> {
                checkFavoriteCoin(event.coinId)
            }
        }
    }

    private fun favoriteButtonClick() {
        _uiState.value.isFavoriteCoin.let {
            if (it!!)
                removeFavoriteCoin()
            else
                addFavoriteCoin()
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

    private fun checkFavoriteCoin(coinId: String) = viewModelScope.launch {
        checkFavoriteUseCase.invoke(coinId).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    _uiState.update { it.copy(isFavoriteCoin = result.data, isLoading = false) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message.toString(), isLoading = false) }
                }
            }
        }
    }

    private fun addFavoriteCoin() {
        viewModelScope.launch {
            addFavoriteUseCase.invoke(_uiState.value.coin.toFavoriteCoin()).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(isFavoriteCoin = true, isLoading = false) }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(error = result.message.toString(), isLoading = false) }
                    }
                }
            }
        }
    }

    private fun removeFavoriteCoin() = viewModelScope.launch {
        if (!_uiState.value.coin?.id.isNullOrEmpty()) {
            removeFavoriteUseCase.invoke(_uiState.value.coin?.id!!).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(isFavoriteCoin = false, isLoading = false) }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(error = result.message.toString(), isLoading = false) }
                    }
                }
            }
        }
    }
}