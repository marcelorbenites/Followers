package com.marcerlorbenites.followers

import android.app.Application
import com.marcerlorbenites.followers.http.HttpFollowerService
import com.marcerlorbenites.followers.http.JsonObjectConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient

class FollowerApplication : Application(), DependencyManager {

    override val followerManager: FollowerManager by lazy {
        PlayerFollowerManager(
            HttpFollowerService(BuildConfig.FOLLOWER_BASE_URL, OkHttpClient(), JsonObjectConverter()),
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }

    override val followersLoadOffset: Int by lazy {
        BuildConfig.FOLLOWERS_LOAD_OFFSET
    }

    override fun onCreate() {
        super.onCreate()
        followerManager.setup()
    }
}
