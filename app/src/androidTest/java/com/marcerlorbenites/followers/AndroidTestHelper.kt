package com.marcerlorbenites.followers

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onIdle
import androidx.test.rule.ActivityTestRule
import com.marcerlorbenites.followers.http.HttpFollowerService
import com.marcerlorbenites.followers.http.JsonObjectConverter
import com.marcerlorbenites.followers.list.FollowerListScrollController
import com.marcerlorbenites.followers.rx.RxJavaDispatcher
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient

class AndroidTestHelper {
    companion object {

        fun <T : Activity> launchActivity(
            rule: ActivityTestRule<T>,
            baseUrl: String,
            followersLoadOffset: Int,
            imageLoader: ImageLoader
        ) {
            val application = ApplicationProvider.getApplicationContext<FollowerApplication>()

            val mainThreadScheduler = AndroidSchedulers.mainThread()

            val followerManager = PlayerFollowerManager(
                HttpFollowerService(baseUrl, OkHttpClient(), JsonObjectConverter()),
                ServiceErrorFactory(),
                RxJavaDispatcher(mainThreadScheduler, mainThreadScheduler)
            )

            setLazyDependency(application, "followerManager", followerManager)
            setLazyDependency(application, "imageLoader", imageLoader)
            setLazyDependency(
                application,
                "scrollController",
                FollowerListScrollController(followerManager, followersLoadOffset)
            )

            enableNetworkOnMainThread()

            rule.launchActivity(Intent())

            onIdle {
                followerManager.setup()
            }
        }

        private fun enableNetworkOnMainThread() {
            // This is necessary due to StrictMode policy of Android that prevents us from running network operations
            // on main thread so to change the policy on the android's thread we must to post the runnable which changes
            // the policy into main looper.
            Handler(Looper.getMainLooper()).post {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

                StrictMode.setThreadPolicy(policy)
            }
        }

        private fun <T> setLazyDependency(
            application: Application,
            lazyFieldName: String,
            lazyValue: T
        ) {
            val field = application.javaClass.getDeclaredField("$lazyFieldName\$delegate")
            field.isAccessible = true
            field.set(application, object : Lazy<T> {

                override val value: T = lazyValue

                override fun isInitialized() = true
            })
        }
    }
}
