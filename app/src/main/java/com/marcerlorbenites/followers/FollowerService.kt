package com.marcerlorbenites.followers

interface FollowerService {
    fun getFollowers(): List<Follower>
    fun getNextFollowers(lastFollowerId: String): List<Follower>
}
