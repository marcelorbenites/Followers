package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.Follower

data class FollowerItemViewModel(private val follower: Follower, private val noClubText: String) {

    val id: String = follower.id
    val clubName: String = if (follower.hasClub) {
        follower.club!!.name
    } else {
        noClubText
    }
    val picture: String = follower.picture
    val fullName: String = follower.fullName
}
