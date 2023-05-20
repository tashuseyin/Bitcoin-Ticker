package com.example.bitcoin_ticker.core

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import com.example.bitcoin_ticker.R
import com.google.android.material.snackbar.Snackbar


fun showSnackbar(context: Context, view: View, message: String, isSuccess: Boolean) {
    val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    val snackbarView = snackbar.view
    if (isSuccess) {
        snackbarView.setBackgroundColor(getColor(context, R.color.green))
    } else {
        snackbarView.setBackgroundColor(getColor(context, R.color.red))
    }
    val textView =
        snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snackbar.show()
}
