package com.marcerlorbenites.followers.view

import android.widget.ImageView

interface ImageLoader {
    fun load(imageView: ImageView, pictureUrl: String, reference: String)
    fun cancel(reference: String)
}
