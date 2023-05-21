package com.example.bitcoin_ticker.core.extension

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load

fun ImageView.loadImageView(url: String?, progressDrawable: CircularProgressDrawable) {
    if (url != null) {
        this.load(url) {
            placeholder(progressDrawable)
            crossfade(false)
        }
    }
}
