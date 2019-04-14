package com.marcerlorbenites.followers.list

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marcerlorbenites.followers.*
import com.marcerlorbenites.followers.detail.FollowerDetailContainer

class TestActivity : AppCompatActivity(), FollowerListContainer, FollowerDetailContainer {

    var testFollowerManager: FollowerManager = FakeFollowerManager()

    var testImageLoader: ImageLoader = FakeImageLoader()

    var testFollowersLoadOffset: Int = 10

    override val followerManager: FollowerManager by lazy {
        testFollowerManager
    }

    override val imageLoader: ImageLoader by lazy {
        testImageLoader
    }

    override val followersLoadOffset: Int by lazy {
        testFollowersLoadOffset
    }

    override val navigator: Navigator = Navigator(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        val view = LinearLayout(this)
        view.id = 1
        setContentView(view)
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(1, fragment)
            .commit()
    }
}
