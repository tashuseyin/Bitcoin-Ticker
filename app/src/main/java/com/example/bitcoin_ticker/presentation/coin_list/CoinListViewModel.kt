package com.example.bitcoin_ticker.presentation.coin_list

import androidx.lifecycle.*
import com.example.bitcoin_ticker.core.Resource
import com.example.bitcoin_ticker.domain.mapper.toEntity
import com.example.bitcoin_ticker.domain.mapper.toUIModel
import com.example.bitcoin_ticker.domain.model.CoinListItemUIModel
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import com.example.bitcoin_ticker.domain.use_case.coins.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinListUseCase: GetCoinListUseCase,
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinListUIState())
    val uiState: StateFlow<CoinListUIState> = _uiState

    // Database
    val coinList: LiveData<List<CoinListItemUIModel>> =
        coinRepository.getAllDatabaseCoins().map { it.toUIModel() }.asLiveData()

    private val _searchQuery = MutableLiveData<String>()

    private val _searchResult = _searchQuery.switchMap { query ->
        coinRepository.getSearchCoinList(query).map { it.toUIModel() }.asLiveData()
    }
    val searchResult: LiveData<List<CoinListItemUIModel>> = _searchResult

    fun onEvent(event: CoinListUIEvent) {
        when (event) {
            is CoinListUIEvent.LoadCoins -> {
                getCoins()
            }
            is CoinListUIEvent.LoadSearchCoinList -> {
                setSearchQuery(event.query)
            }
        }
    }

    private fun setSearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    private fun insertCoinItemList(coinListItemUIModel: List<CoinListItemUIModel>) =
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.insertAllCoins(coinListItemUIModel.toEntity())
        }

    private fun getCoins() {
        getCoinListUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.value = CoinListUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _uiState.value = CoinListUIState(coins = result.data ?: emptyList())
                    insertCoinItemList(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value =
                        CoinListUIState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}