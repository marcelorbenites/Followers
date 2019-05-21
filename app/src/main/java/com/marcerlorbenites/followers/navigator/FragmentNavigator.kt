package com.marcerlorbenites.followers.navigator

import androidx.fragment.app.FragmentManager
import com.marcerlorbenites.followers.R
import com.marcerlorbenites.followers.detail.FollowerDetailFragment
import com.marcerlorbenites.followers.list.FollowerListFragment

class FragmentNavigator(private val fragmentManager: FragmentManager) :
    Navigator {
    override fun navigateToFollowerListView() {
        fragmentManager.beginTransaction()
            .replace(
                R.id.activity_follower_container,
                FollowerListFragment()
            )
            .commit()
    }

    override fun navigateToFollowerDetailView() {
        fragmentManager.beginTransaction()
            .replace(
                R.id.activity_follower_container,
                FollowerDetailFragment()
            )
            .addToBackStack("FollowerList->FollowerDetail")
            .commit()
    }

    override fun navigateBack() {
        fragmentManager.popBackStack()
    }
}
