package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Follower

class FakeJsonConverter(private val followerList: List<Follower> = emptyList()): JsonConverter {
    override fun parseFollowers(json: String) = followerList
}

