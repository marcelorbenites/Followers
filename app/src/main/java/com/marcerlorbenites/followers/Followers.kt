package com.marcerlorbenites.followers

data class Followers(
    val list: List<Follower>,
    val selectedFollower: Follower? = null
) {
    val last: Follower? = list.lastOrNull()
    val followerSelected: Boolean = selectedFollower != null

    fun findFollower(followerId: String): Follower {
        return list.find { follower ->
            follower.id == followerId
        } ?: throw IllegalStateException("Follower with id $followerId not found.")
    }
}
