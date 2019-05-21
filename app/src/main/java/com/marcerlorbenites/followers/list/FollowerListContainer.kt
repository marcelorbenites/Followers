package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.FollowerManager
import com.marcerlorbenites.followers.navigator.FragmentNavigator
import com.marcerlorbenites.followers.ImageLoader

interface FollowerListContainer {
    val scrollController: FollowerListScrollController
    val followerManager: FollowerManager
    val imageLoader: ImageLoader
    val navigator: FragmentNavigator
}
