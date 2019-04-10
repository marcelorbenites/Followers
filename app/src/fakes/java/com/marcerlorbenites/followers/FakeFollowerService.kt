package com.marcerlorbenites.followers

class FakeFollowerService(private val followers: List<Follower> = emptyList()) : FollowerService {
    override fun getFollowers() = followers
}
