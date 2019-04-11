package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Club
import com.marcerlorbenites.followers.Follower
import org.json.JSONObject

class JsonObjectConverter : JsonConverter {
    override fun parseFollowers(json: String): List<Follower> {
        val followers = mutableListOf<Follower>()

        val responseJson = JSONObject(json)
        val followersJson = responseJson.getJSONArray("response")

        var followerJson: JSONObject
        var clubJson: JSONObject

        var club: Club?
        for (index in 0 until followersJson.length()) {
            followerJson = followersJson.getJSONObject(index)
            club = null

            if (!followerJson.isNull("club")) {
                clubJson = followerJson.getJSONObject("club")
                if (!clubJson.isNull("name") && !clubJson.isNull("logo_url")) {
                    club = Club(clubJson.getString("name"), clubJson.getString("logo_url"))
                }
            }

            followers.add(
                Follower(
                    followerJson.getString("slug"),
                    followerJson.getString("firstname"),
                    followerJson.getString("lastname"),
                    followerJson.getString("profile_picture"),
                    club
                )
            )
        }
        return followers
    }
}
