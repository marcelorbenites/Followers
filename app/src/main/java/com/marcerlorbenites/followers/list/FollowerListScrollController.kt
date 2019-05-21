package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.FollowerManager

class FollowerListScrollController(
    private val followerManager: FollowerManager,
    private val itemOffset: Int
) {
    fun onScroll(canScrollDown: Boolean, itemCount: Int, lastVisibleItemPosition: Int) {
        if (canScrollDown) {
            val lastItemPosition = itemCount - 1
            if ((lastItemPosition - lastVisibleItemPosition) <= itemOffset) {
                followerManager.loadMoreFollowers()
            }
        }
    }
}
