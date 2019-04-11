package com.marcerlorbenites.followers.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcerlorbenites.followers.DependencyManager
import com.marcerlorbenites.followers.FollowerManager
import com.marcerlorbenites.followers.R
import com.marcerlorbenites.followers.view.picasso.PicassoImageLoader
import com.squareup.picasso.Picasso

class FollowerActivity: AppCompatActivity(), FollowerListViewContainer {

    override val followerManager: FollowerManager by lazy {
        (application as DependencyManager).followerManager
    }

    override val imageLoader: ImageLoader by lazy {
        PicassoImageLoader(Picasso.get())
    }

    private val navigator: Navigator by lazy {
        Navigator(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follower)

        if (savedInstanceState == null) {
            navigator.navigateToFollowerListView()
        }
    }
}
