package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.FollowerManager
import com.marcerlorbenites.followers.ImageLoader

interface FollowerListContainer {
    val followerManager: FollowerManager
    val imageLoader: ImageLoader
    val navigator: Navigator
    val followersLoadOffset: Int
}
