package com.marcerlorbenites.followers

data class Follower(
    val id: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val club: Club? = null
) {
    val fullName = "$firstName $lastName"
    val hasClub = club != null
}
