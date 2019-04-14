package com.marcerlorbenites.followers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marcerlorbenites.followers.AndroidTestHelper.Companion.launchActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FollowerDetailComponentTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<FollowerActivity> = ActivityTestRule(FollowerActivity::class.java, true, false)

    @Test
    fun shouldDisplaySelectedFollowerOnFollowerClick() {
        val server = MockWebServer()
        server.start()

        server.enqueue(MockResponse().setBody(TestData.FOLLOWERS_JSON))

        launchActivity(
            rule,
            server.url("/").toString(),
            5,
            FakeImageLoader()
        )

        onView(withChild(withText("John Lennon"))).perform(click())
        onView(withText(R.string.fragment_follower_detail_title)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withText("John Lennon")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        server.shutdown()
    }
}
