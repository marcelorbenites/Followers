package com.marcerlorbenites.followers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcerlorbenites.followers.detail.FollowerDetailContainer
import com.marcerlorbenites.followers.list.FollowerListContainer
import com.marcerlorbenites.followers.list.Navigator
import com.marcerlorbenites.followers.picasso.PicassoImageLoader
import com.squareup.picasso.Picasso

class FollowerActivity : AppCompatActivity(), FollowerListContainer, FollowerDetailContainer {

    override val followerManager: FollowerManager by lazy {
        (application as DependencyManager).followerManager
    }

    override val followersLoadOffset: Int by lazy {
        (application as DependencyManager).followersLoadOffset
    }

    override val imageLoader: ImageLoader by lazy {
        PicassoImageLoader(Picasso.get())
    }

    override val navigator: Navigator by lazy {
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
