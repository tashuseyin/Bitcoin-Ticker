package com.example.bitcoin_ticker.presentation.coin_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bitcoin_ticker.R
import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.core.extension.loadImageView
import com.example.bitcoin_ticker.core.extension.placeholderProgressBar
import com.example.bitcoin_ticker.databinding.FragmentCoinDetailBinding
import com.example.bitcoin_ticker.domain.model.CoinDetailItemUIModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!
    private val coinDetailViewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventListener()
        observeViewModel()
    }

    private fun eventListener() {
        binding.saveIcon.setOnClickListener {
            coinDetailViewModel.onEvent(CoinDetailUIEvent.OnClickFavoriteButton)
        }
        binding.closeIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            coinDetailViewModel.uiState.collect {
                if (it.coin != null) {
                    setUIComponent(it.coin)
                }
                if (it.error.isNotBlank()) {
                    binding.errorText.text = it.error
                }
                binding.progressBar.isVisible = it.isLoading

                it.isFavoriteCoin?.let { bool ->
                    updateSaveIcon(bool)
                }
            }
        }
    }

    private fun updateSaveIcon(bool: Boolean) {
        if (bool) {
            binding.saveIcon.setImageResource(R.drawable.bookmark_fill)
        } else {
            binding.saveIcon.setImageResource(R.drawable.bookmark)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUIComponent(coin: CoinDetailItemUIModel?) {
        binding.apply {
            coinName.text = coin?.name ?: Constant.NOTHING
            coinSymbol.text = coin?.symbol ?: Constant.NOTHING
            coinImage.loadImageView(coin?.image?.large, placeholderProgressBar(requireContext()))
            coinPrice.text = "$" + String.format("%.6s", coin?.marketData?.currentPrice?.usd.toString())
            convertPricePercentage(coin?.marketData?.priceChangePercentage24h)
            hashingAlgorithm.text = coin?.hashingAlgorithm ?: Constant.NOTHING
            description.text = coin?.description?.en ?: Constant.NOTHING
        }
    }

    @SuppressLint("SetTextI18n")
    private fun convertPricePercentage(priceChangePercentage24h: Double?) {
        if (priceChangePercentage24h.toString().startsWith("-")) {
            binding.pricePercentage.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
        } else {
            binding.pricePercentage.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        binding.pricePercentage.text = String.format("%.3f", priceChangePercentage24h) + "%"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}