package com.marcerlorbenites.followers.view

import android.widget.ImageView

class FakeImageLoader : ImageLoader {
    override fun load(imageView: ImageView, pictureUrl: String, reference: String) {
    }

    override fun cancel(reference: String) {
    }
}

