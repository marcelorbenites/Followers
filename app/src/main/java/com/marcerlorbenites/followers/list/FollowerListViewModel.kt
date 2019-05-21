package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.Followers

data class FollowerListViewModel(private val followers: Followers, private val noClubText: String) {
    val list: List<FollowerItemViewModel> = followers.list.map { follower ->
        FollowerItemViewModel(follower, noClubText)
    }
}
