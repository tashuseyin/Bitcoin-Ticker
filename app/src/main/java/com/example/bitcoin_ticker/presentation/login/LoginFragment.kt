package com.example.bitcoin_ticker.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bitcoin_ticker.R
import com.example.bitcoin_ticker.core.showSnackbar
import com.example.bitcoin_ticker.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate()
        observeUI()
        eventListener()
        collectUIState()
    }

    private fun navigate() {
        binding.registerText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            loginViewModel.uiState.collect { uiState ->
                if (uiState.authResponse != null) {
                    showSnackbar(
                        requireContext(),
                        requireView(),
                        getString(uiState.authResponse.message),
                        true
                    )
                    delay(200)
                    findNavController().navigate(R.id.action_loginFragment_to_coinListFragment)
                }
                if (uiState.error.isNotBlank()) {
                    showSnackbar(
                        requireContext(),
                        requireView(),
                        getString(uiState.error.toInt()),
                        false
                    )
                }
                binding.progressBar.isVisible = uiState.isLoading
            }
        }
    }

    private fun observeUI() {
        loginViewModel.apply {
            loginButtonEnabled.observe(viewLifecycleOwner) {
                binding.loginButton.isEnabled = it
            }
        }
    }

    private fun eventListener() {
        binding.loginEmailEdittext.addTextChangedListener {
            loginViewModel.onEvent(LoginUIEvent.EnteringEmail(it.toString()))
        }
        binding.loginPasswordEdittext.addTextChangedListener {
            loginViewModel.onEvent(LoginUIEvent.EnteringPassword(it.toString()))
        }
        binding.loginButton.setOnClickListener {
            loginViewModel.onEvent(LoginUIEvent.Login)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}