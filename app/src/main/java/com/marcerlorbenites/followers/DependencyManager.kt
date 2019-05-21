package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.list.FollowerListScrollController

interface DependencyManager {
    val scrollController: FollowerListScrollController
    val followerManager: FollowerManager
    val imageLoader: ImageLoader
}
