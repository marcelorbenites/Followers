package com.marcerlorbenites.followers

class FakeFollowerService(
    private val followers: List<Follower> = emptyList(),
    private val nextFollowers: List<Follower> = emptyList()
) : FollowerService {
    override fun getFollowers() = followers
    override fun getNextFollowers(lastFollowerId: String) = nextFollowers
}
