package com.marcerlorbenites.followers

data class Followers(
    val list: List<Follower>,
    private val selectedFollowerId: String = ""
) {
    val last: Follower? = list.lastOrNull()
    val selectedFollower: Follower? = list.find { follower ->
        follower.id == selectedFollowerId
    }
    val followerSelected: Boolean = selectedFollower != null
}
