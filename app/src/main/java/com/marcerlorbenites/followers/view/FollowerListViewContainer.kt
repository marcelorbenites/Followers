package com.marcerlorbenites.followers.view

import com.marcerlorbenites.followers.FollowerManager

interface FollowerListViewContainer {
    val followerManager: FollowerManager
    val imageLoader: ImageLoader
}
