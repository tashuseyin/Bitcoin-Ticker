package com.example.bitcoin_ticker.core.extension

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.bitcoin_ticker.R


fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        setColorSchemeColors(R.color.purple_600)
        start()
    }
}