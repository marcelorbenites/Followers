package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.FollowerManager
import com.marcerlorbenites.followers.ImageLoader

interface FollowerListViewContainer {
    val followerManager: FollowerManager
    val imageLoader: ImageLoader
}
