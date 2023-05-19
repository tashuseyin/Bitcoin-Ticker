package com.example.bitcoin_ticker.core.extension

import android.widget.ImageView
import coil.load

fun ImageView.loadImageView(url: String?) {
    if (url != null) {
        this.load(url) {
            placeholder(null)
            crossfade(false)
        }
    }
}
