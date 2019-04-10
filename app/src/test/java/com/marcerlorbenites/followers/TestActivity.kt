package com.marcerlorbenites.followers

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class TestActivity : AppCompatActivity(), FollowerListViewContainer {

    var testFollowerManager: FollowerManager = FakeFollowerManager()

    override val followerManager: FollowerManager by lazy {
        testFollowerManager
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
