package com.marcerlorbenites.followers.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marcerlorbenites.followers.FakeFollowerManager
import com.marcerlorbenites.followers.FollowerManager
import com.marcerlorbenites.followers.R

class TestActivity : AppCompatActivity(), FollowerListViewContainer {

    var testFollowerManager: FollowerManager = FakeFollowerManager()

    var testImageLoader: ImageLoader = FakeImageLoader()

    override val followerManager: FollowerManager by lazy {
        testFollowerManager
    }

    override val imageLoader: ImageLoader by lazy {
        testImageLoader
    }

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
