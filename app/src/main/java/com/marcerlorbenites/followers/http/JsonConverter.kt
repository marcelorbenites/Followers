package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Follower

interface JsonConverter {
    fun parseFollowers(json: String): List<Follower>
}
