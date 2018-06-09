package com.mobile.shopaid.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 10:24
 */

fun ImageView.loadImage(url: String) {
    Picasso.get()
            .load(prependSchemeToUrl(url))
            .into(this)
}

fun prependSchemeToUrl(url: String): String {
    val scheme = "http:"
    if (!url.startsWith(scheme) && !url.startsWith(scheme)) {
        return scheme + url
    }
    return url
}