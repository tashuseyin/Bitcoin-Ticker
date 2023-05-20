package com.example.bitcoin_ticker.presentation.favorite_coins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
        favoriteCoinsViewModel.onEvent(FavoriteCoinsUIEvent.LoadFavoriteCoins)
        setRecyclerview()
        eventListener()
        observeUIState()
    }

    private fun setRecyclerview() {
        binding.recyclerView.adapter = favoriteCoinsAdapter
    }

    private fun eventListener(){
        favoriteCoinsAdapter.onItemClick = {
            findNavController().navigate(FavoriteCoinsFragmentDirections.actionFavoriteCoinsFragmentToCoinDetailFragment(it.toString()))
        }
    }

    private fun observeUIState() {
        lifecycleScope.launch {
            favoriteCoinsViewModel.uiState.collect { uiState ->
                if (uiState.coins.isNotEmpty()) {
                    binding.recyclerView.isVisible = true
                    favoriteCoinsAdapter.submitList(uiState.coins)
                } else {
                    binding.recyclerView.isVisible = false
                    binding.errorText.text = getString(R.string.empty_favorite_coins)
                }

                if (uiState.error.isNotBlank()) {
                    binding.errorLayout.isVisible = true
                    binding.errorText.text = uiState.error
                }
                binding.progressBar.isVisible = uiState.isLoading
                binding.errorLayout.isVisible = uiState.isShowErrorLayout
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}