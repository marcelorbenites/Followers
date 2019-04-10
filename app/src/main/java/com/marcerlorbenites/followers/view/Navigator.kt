package com.marcerlorbenites.followers.view

import androidx.fragment.app.FragmentManager
import com.marcerlorbenites.followers.R

class Navigator(private val fragmentManager: FragmentManager) {
    fun navigateToFollowerListView() {
        fragmentManager.beginTransaction()
            .replace(
                R.id.activity_follower_container,
                FollowerListFragment()
            )
            .addToBackStack("None->FollowerList")
            .commit()
    }
}
