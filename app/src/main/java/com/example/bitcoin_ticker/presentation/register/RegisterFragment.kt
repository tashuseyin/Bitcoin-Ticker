package com.example.bitcoin_ticker.presentation.register

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
import com.example.bitcoin_ticker.core.validation.PasswordRule
import com.example.bitcoin_ticker.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventListener()
        collectUIState()
        observeUI()
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            registerViewModel.uiState.collect { uiState ->
                if (uiState.registerResult != null) {
                    findNavController().navigate(R.id.action_registerFragment_to_coinListFragment)
                }
                if (uiState.error.isNotBlank()) {
                    Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
                }
                binding.progressBar.isVisible = uiState.isLoading
            }
        }
    }

    private fun observeUI() {
        registerViewModel.apply {
            registerButtonEnabled.observe(viewLifecycleOwner) {
                binding.registerButton.isEnabled = it
            }
            passwordRules.observe(viewLifecycleOwner) {
                checkPasswordRule(it)
            }
        }
    }

    private fun checkPasswordRule(passwordRule: List<PasswordRule>) {
        val bindingMap = mapOf(
            0 to binding.passwordRuleOneImage,
            1 to binding.passwordRuleTwoImage,
            2 to binding.passwordRuleThreeImage,
            3 to binding.passwordRuleFourImage
        )
        for (i in 0 until minOf(passwordRule.size, bindingMap.size)) {
            val imageView = bindingMap[i]
            val statusDrawable = if (passwordRule[i].status) {
                R.drawable.circle_check_solid
            } else {
                R.drawable.circle
            }
            imageView?.setImageResource(statusDrawable)
        }
    }

    private fun eventListener() {
        binding.registerEmailEdittext.addTextChangedListener {
            registerViewModel.onEvent(RegisterUIEvent.EnteringEmail(it.toString()))
        }
        binding.registerPasswordEdittext.addTextChangedListener {
            registerViewModel.onEvent(RegisterUIEvent.EnteringPassword(it.toString()))
        }
        binding.registerButton.setOnClickListener {
            registerViewModel.onEvent(RegisterUIEvent.Register)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
