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
        var teamJson: JSONObject
        var clubJson: JSONObject
        var leagueJson: JSONObject

        for (index in 0 until followersJson.length()) {
            followerJson = followersJson.getJSONObject(index)
            teamJson = followerJson.getJSONObject("team")
            clubJson = teamJson.getJSONObject("club")
            leagueJson = teamJson.getJSONObject("league")
            followers.add(
                Follower(
                    followerJson.getString("slug"),
                    followerJson.getString("firstname"),
                    followerJson.getString("lastname"),
                    followerJson.getString("profile_picture"),
                    Club(clubJson.getString("name"), clubJson.getString("logo_url")),
                    leagueJson.getString("name")
                )
            )
        }
        return followers
    }
}
