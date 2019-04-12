package com.marcerlorbenites.followers.detail

import com.marcerlorbenites.followers.FollowerManager
import com.marcerlorbenites.followers.ImageLoader
import com.marcerlorbenites.followers.list.Navigator

interface FollowerDetailContainer {
    val followerManager: FollowerManager
    val imageLoader: ImageLoader
    val navigator: Navigator
}
