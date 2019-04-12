package com.marcerlorbenites.followers

import android.widget.ImageView
import com.marcerlorbenites.followers.ImageLoader

class FakeImageLoader : ImageLoader {
    override fun load(imageView: ImageView, pictureUrl: String, reference: String) {
    }

    override fun cancel(reference: String) {
    }
}

