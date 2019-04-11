package com.marcerlorbenites.followers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marcerlorbenites.followers.AndroidTestHelper.Companion.launchActivity
import com.marcerlorbenites.followers.ViewMatchers.Companion.withHolderView
import com.marcerlorbenites.followers.view.FollowerActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FollowerListComponentTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<FollowerActivity> = ActivityTestRule(FollowerActivity::class.java, true, false)

    @Test
    fun shouldDisplayFollowersOnApplicationStart() {
        val server = MockWebServer()
        server.start()

        server.enqueue(MockResponse().setBody(TestData.FOLLOWERS_JSON))

        launchActivity(
            rule,
            server.url("/").toString()
        )

        onView(withText("John Lennon")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(allOf(withParent(withChild(withText("John Lennon"))), withText("The Beatles F.C.")))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withText("Ringo Starr")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(allOf(withParent(withChild(withText("Ringo Starr"))), withText("The Beatles F.C.")))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        server.shutdown()
    }

    @Test
    fun shouldDisplayMoreFollowersWhenScrollToTheEndOfList() {
        val server = MockWebServer()
        server.start()

        server.enqueue(MockResponse().setBody(TestData.FIRST_FOLLOWERS_JSON))
        server.enqueue(MockResponse().setBody(TestData.SECOND_FOLLOWERS_JSON))

        launchActivity(
            rule,
            server.url("/").toString()
        )

        onView(withId(R.id.followerList))
            .perform(scrollToHolder(withHolderView(hasDescendant(withText("Abdal-Malik Kassim  Madi")))))

        onView(withId(R.id.followerList))
            .perform(scrollToHolder(withHolderView(hasDescendant(withText("Abdelaat Ifkharne")))))

        server.shutdown()
    }
}
