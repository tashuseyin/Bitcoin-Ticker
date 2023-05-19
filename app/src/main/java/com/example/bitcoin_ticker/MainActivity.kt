package com.example.bitcoin_ticker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.bitcoin_ticker.core.extension.hideBottomNavigation
import com.example.bitcoin_ticker.core.extension.showBottomNavigation
import com.example.bitcoin_ticker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()
        controlBottomNavigationVisibility()
    }

    private fun setBottomNavigation() {
        val navController = findNavController(R.id.navigation_fragment_activity_main)
        AppBarConfiguration.Builder(
            R.id.coinListFragment,
            R.id.favoriteCoinsFragment
        ).build()

        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun controlBottomNavigationVisibility() {
        val bottomNavDestinations = setOf(R.id.coinListFragment, R.id.favoriteCoinsFragment)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_fragment_activity_main) as NavHostFragment

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in bottomNavDestinations) {
                binding.bottomNavigation.showBottomNavigation()
            } else {
                binding.bottomNavigation.hideBottomNavigation()
            }
        }
    }
}