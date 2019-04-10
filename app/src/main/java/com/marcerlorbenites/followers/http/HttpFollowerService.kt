package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Follower
import com.marcerlorbenites.followers.FollowerService
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class HttpFollowerService(
    private val url: String,
    private val httpClient: OkHttpClient,
    private val jsonConverter: JsonConverter
) : FollowerService {
    override fun getFollowers(): List<Follower> {
        val request = Request.Builder()
            .url("${url}followers")
            .get()
            .build()

        val response = httpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            throw IOException()
        } else {
            return jsonConverter.parseFollowers(response.body()!!.string())
        }
    }
}
