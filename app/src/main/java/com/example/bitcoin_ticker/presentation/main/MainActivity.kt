package com.example.bitcoin_ticker.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.bitcoin_ticker.R
import com.example.bitcoin_ticker.core.extension.hideBottomNavigation
import com.example.bitcoin_ticker.core.extension.showBottomNavigation
import com.example.bitcoin_ticker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
        checkUser()
        setBottomNavigation()
        controlBottomNavigationVisibility()
        onItemSelectedListener()
    }

    private fun setNavigation() {
        navController = findNavController(R.id.navigation_fragment_activity_main)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment_activity_main) as NavHostFragment
    }

    private fun onItemSelectedListener() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.coinListFragment -> {
                    changeNavigationStartDestination(R.id.coinListFragment)
                    true
                }
                R.id.favoriteCoinsFragment -> {
                    changeNavigationStartDestination(R.id.favoriteCoinsFragment)
                    true
                }
                R.id.logout -> {
                    mainViewModel.onEvent(MainUIEvent.Logout)
                    changeNavigationStartDestination(R.id.loginFragment)
                    true
                }
                else -> false
            }
        }
    }


    private fun checkUser() {
        lifecycleScope.launch {
            mainViewModel.isCurrentUser.collect {
                changeNavigationStartDestination(
                    if (it) {
                        R.id.coinListFragment
                    } else {
                        R.id.loginFragment
                    }
                )
            }
        }
    }

    private fun changeNavigationStartDestination(startDestinationId: Int) {
        navHostFragment.findNavController().apply {
            val navGraph = navInflater.inflate(R.navigation.nav_graph)
            navGraph.setStartDestination(startDestinationId)
            graph = navGraph
        }
    }

    private fun setBottomNavigation() {
        AppBarConfiguration.Builder(
            R.id.coinListFragment,
            R.id.favoriteCoinsFragment
        ).build()

        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun controlBottomNavigationVisibility() {
        val bottomNavDestinations = setOf(R.id.coinListFragment, R.id.favoriteCoinsFragment)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment_activity_main) as NavHostFragment

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in bottomNavDestinations) {
                binding.bottomNavigation.showBottomNavigation()
            } else {
                binding.bottomNavigation.hideBottomNavigation()
            }
        }
    }
}