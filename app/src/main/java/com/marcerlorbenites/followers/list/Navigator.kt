package com.marcerlorbenites.followers.list

import androidx.fragment.app.FragmentManager
import com.marcerlorbenites.followers.R
import com.marcerlorbenites.followers.detail.FollowerDetailFragment

class Navigator(private val fragmentManager: FragmentManager) {
    fun navigateToFollowerListView() {
        fragmentManager.beginTransaction()
            .replace(
                R.id.activity_follower_container,
                FollowerListFragment()
            )
            .commit()
    }

    fun navigateToFollowerDetailView() {
        fragmentManager.beginTransaction()
            .replace(
                R.id.activity_follower_container,
                FollowerDetailFragment()
            )
            .addToBackStack("FollowerList->FollowerDetail")
            .commit()
    }

    fun navigateBack() {
        fragmentManager.popBackStack()
    }
}
