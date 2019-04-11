package com.marcerlorbenites.followers

data class Followers(val list: List<Follower>) {
    val last: Follower? = list.lastOrNull()
}
