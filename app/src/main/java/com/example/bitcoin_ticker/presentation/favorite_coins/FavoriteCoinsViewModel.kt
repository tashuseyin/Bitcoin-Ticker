package com.example.bitcoin_ticker.presentation.favorite_coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.use_case.coins.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCoinsViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteCoinsUIState())
    val uiState: StateFlow<FavoriteCoinsUIState> = _uiState

    fun onEvent(event: FavoriteCoinsUIEvent) {
        when (event) {
            is FavoriteCoinsUIEvent.LoadFavoriteCoins -> {
                getFavoriteCoins()
            }
        }
    }

    private fun getFavoriteCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = FavoriteCoinsUIState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _uiState.value = FavoriteCoinsUIState(
                            coins = result.data ?: emptyList(),
                            isShowErrorLayout = result.data?.isEmpty() == true
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = FavoriteCoinsUIState(
                            error = result.message.toString(),
                            isShowErrorLayout = true
                        )
                    }
                }
            }
        }
    }
}