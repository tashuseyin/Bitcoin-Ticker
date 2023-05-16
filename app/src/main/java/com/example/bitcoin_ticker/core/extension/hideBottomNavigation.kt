package com.example.bitcoin_ticker.core.extension

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.hideBottomNavigation() {
    this.visibility = View.GONE
}

fun BottomNavigationView.showBottomNavigation() {
    this.visibility = View.VISIBLE
}
