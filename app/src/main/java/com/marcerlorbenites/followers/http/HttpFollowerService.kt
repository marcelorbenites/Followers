package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Follower
import com.marcerlorbenites.followers.FollowerService
import okhttp3.OkHttpClient
import okhttp3.Request

class HttpFollowerService(
    private val url: String,
    private val httpClient: OkHttpClient,
    private val jsonConverter: JsonConverter
) : FollowerService {
    override fun getFollowers(): List<Follower> {
        val request = Request.Builder()
            .url("${url}Followers/db")
            .get()
            .build()

        val response = httpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            throw IllegalStateException("Failed to get followers from server with status code: ${response.code()}")
        } else {
            return jsonConverter.parseFollowers(response.body()!!.string())
        }
    }

    override fun getNextFollowers(lastFollowerId: String): List<Follower> {
        val request = Request.Builder()
            .url("${url}SecondPage/db")
            .get()
            .build()

        val response = httpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            throw IllegalStateException("Failed to get next followers from server with status code: ${response.code()}")
        } else {
            return jsonConverter.parseFollowers(response.body()!!.string())
        }
    }
}
