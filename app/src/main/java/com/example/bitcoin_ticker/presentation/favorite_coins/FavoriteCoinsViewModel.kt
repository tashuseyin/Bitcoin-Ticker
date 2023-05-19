package com.example.bitcoin_ticker.presentation.favorite_coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.use_case.coins.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCoinsViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteCoinsUIState())
    val uiState: StateFlow<FavoriteCoinsUIState> = _uiState

    init {
        onEvent(FavoriteCoinsUIEvent.LoadFavoriteCoins)
    }

    fun onEvent(event: FavoriteCoinsUIEvent) {
        when (event) {
            is FavoriteCoinsUIEvent.LoadFavoriteCoins -> {
                getFavoriteCoins()
            }
        }
    }

    private fun getFavoriteCoins() {
        viewModelScope.launch {
            getFavoritesUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                coins = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message.toString(),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}