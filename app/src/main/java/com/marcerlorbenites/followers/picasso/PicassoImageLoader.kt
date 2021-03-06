package com.marcerlorbenites.followers.picasso

import android.widget.ImageView
import com.marcerlorbenites.followers.ImageLoader
import com.squareup.picasso.Picasso

class PicassoImageLoader(private val picasso: Picasso): ImageLoader {
    override fun load(imageView: ImageView, pictureUrl: String, reference: String) {
        picasso.load(pictureUrl)
            .tag(reference)
            .into(imageView)
    }

    override fun cancel(reference: String) {
        picasso.cancelTag(reference)
    }
}
