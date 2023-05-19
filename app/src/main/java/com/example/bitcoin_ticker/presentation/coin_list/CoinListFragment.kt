package com.example.bitcoin_ticker.presentation.coin_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

        binding.recyclerView.adapter = coinListAdapter
        observeUIState()
        eventListener()
    }

    private fun eventListener() {
        coinListAdapter.onItemClick = {
            findNavController().navigate(CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(it))
        }
    }


    private fun observeUIState() {
        lifecycleScope.launch {
            coinListViewModel.uiState.collect { uiState ->
                if (uiState.coins.isNotEmpty()) {
                    coinListAdapter.submitList(uiState.coins)
                }
                if (uiState.error.isNotBlank()) {
                    Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
                }
                binding.progressBar.isVisible = uiState.isLoading
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}