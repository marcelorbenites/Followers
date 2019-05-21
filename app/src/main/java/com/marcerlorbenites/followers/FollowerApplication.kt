package com.marcerlorbenites.followers

import android.app.Application
import com.marcerlorbenites.followers.http.HttpFollowerService
import com.marcerlorbenites.followers.http.JsonObjectConverter
import com.marcerlorbenites.followers.list.FollowerListScrollController
import com.marcerlorbenites.followers.picasso.PicassoImageLoader
import com.marcerlorbenites.followers.rx.RxJavaDispatcher
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient

class FollowerApplication : Application(), DependencyManager {

    override val followerManager: FollowerManager by lazy {
        PlayerFollowerManager(
            HttpFollowerService(BuildConfig.FOLLOWER_BASE_URL, OkHttpClient(), JsonObjectConverter()),
            ServiceErrorFactory(),
            RxJavaDispatcher(Schedulers.io(), AndroidSchedulers.mainThread())
        )
    }

    override val scrollController: FollowerListScrollController by lazy {
        FollowerListScrollController(followerManager, BuildConfig.FOLLOWERS_LOAD_OFFSET)
    }

    override val imageLoader: ImageLoader by lazy {
        PicassoImageLoader(Picasso.get())
    }

    override fun onCreate() {
        super.onCreate()
        followerManager.setup()
    }
}
