package com.example.bitcoin_ticker.presentation.favorite_coins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bitcoin_ticker.R
import com.example.bitcoin_ticker.databinding.FragmentFavoriteCoinsBinding
import com.example.bitcoin_ticker.presentation.favorite_coins.adapter.FavoriteCoinsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoriteCoinsFragment : Fragment() {

    private var _binding: FragmentFavoriteCoinsBinding? = null
    private val binding get() = _binding!!
    private val favoriteCoinsViewModel: FavoriteCoinsViewModel by viewModels()
    private val favoriteCoinsAdapter: FavoriteCoinsAdapter = FavoriteCoinsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteCoinsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeUIState()
    }

    private fun initUI() {
        binding.recyclerView.adapter = favoriteCoinsAdapter
    }
    private fun observeUIState() {
        lifecycleScope.launch {
            favoriteCoinsViewModel.uiState.collect { uiState ->
                if (uiState.coins.isNotEmpty()) {
                    binding.errorLayout.isVisible = false
                    favoriteCoinsAdapter.submitList(uiState.coins)
                }
                if (uiState.error.isNotBlank()) {
                    binding.errorLayout.isVisible = true
                    binding.errorText.text = uiState.error
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