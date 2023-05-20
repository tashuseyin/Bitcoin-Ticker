package com.example.bitcoin_ticker.presentation.coin_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bitcoin_ticker.R
import com.example.bitcoin_ticker.core.observeOnce
import com.example.bitcoin_ticker.databinding.FragmentCoinListBinding
import com.example.bitcoin_ticker.presentation.coin_list.adapter.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListFragment : Fragment() {
    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    private val coinListViewModel: CoinListViewModel by viewModels()
    private val coinListAdapter: CoinListAdapter = CoinListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        eventListener()
        observeSearchResult()
        readDatabase()
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = coinListAdapter
        binding.progressBar.isVisible = true
    }

    private fun eventListener() {
        coinListAdapter.onItemClick = {
            findNavController().navigate(
                CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(it)
            )
        }
        binding.searchView.addTextChangedListener {
            if (it.toString().isNotBlank()) {
                coinListViewModel.onEvent(CoinListUIEvent.LoadSearchCoinList(it.toString()))
            } else {
                readDatabase()
            }
        }
    }


    private fun observeUIState() {
        lifecycleScope.launch {
            coinListViewModel.uiState.collect { uiState ->
                if (uiState.error.isNotBlank()) {
                    Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
                }
                if (uiState.coins.isNotEmpty()) {
                    coinListAdapter.submitList(uiState.coins)
                }
                binding.progressBar.isVisible = uiState.isLoading
            }
        }
    }

    private fun observeSearchResult() {
        coinListViewModel.searchResult.observe(viewLifecycleOwner) { searchList ->
            coinListAdapter.submitList(searchList)
            if (searchList.isEmpty()) {
                binding.errorText.text = getString(R.string.search_empty_message)
            }
        }
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            coinListViewModel.coinList.observeOnce(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    coinListAdapter.submitList(database)
                    binding.progressBar.isVisible = false
                } else {
                    coinListViewModel.onEvent(CoinListUIEvent.LoadCoins)
                    observeUIState()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}