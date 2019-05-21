package com.marcerlorbenites.followers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcerlorbenites.followers.detail.FollowerDetailContainer
import com.marcerlorbenites.followers.list.FollowerListContainer
import com.marcerlorbenites.followers.list.FollowerListScrollController
import com.marcerlorbenites.followers.list.Navigator

class FollowerActivity : AppCompatActivity(), FollowerListContainer, FollowerDetailContainer {

    override val followerManager: FollowerManager by lazy {
        (application as DependencyManager).followerManager
    }

    override val imageLoader: ImageLoader by lazy {
        (application as DependencyManager).imageLoader
    }

    override val scrollController: FollowerListScrollController by lazy {
        (application as DependencyManager).scrollController
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
