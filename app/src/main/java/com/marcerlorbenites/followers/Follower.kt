package com.marcerlorbenites.followers

data class Follower(
    val id: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val club: Club,
    val league: String
) {
    val fullName = "$firstName $lastName"
}
