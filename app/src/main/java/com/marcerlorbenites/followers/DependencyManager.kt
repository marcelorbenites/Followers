package com.marcerlorbenites.followers

interface DependencyManager {
    val followerManager: FollowerManager
    val followersLoadOffset: Int
}
