package com.marcerlorbenites.followers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marcerlorbenites.followers.state.State
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class)
class FollowerListFragmentTest {

    @get:Rule
    val rule: ActivityTestRule<TestActivity> = ActivityTestRule(TestActivity::class.java)

    @Test
    fun `Given followers are loaded When view is resumed Then show followers`() {

        rule.activity.testFollowerManager = FakeFollowerManager(
            State(
                State.Name.LOADED,
                Followers(
                    listOf(
                        Follower(
                            "1",
                            "John",
                            "Lennon",
                            "http://thebeatles.com/john",
                            Club("The Beatles F.C.", "http://thebeatles.com/logo"),
                            "Barclays Premier League"
                        ),
                        Follower(
                            "2",
                            "Ringo",
                            "Starr",
                            "http://thebeatles.com/ringo",
                            Club("The Beatles F.C.", "http://thebeatles.com/logo"),
                            "Barclays Premier League"
                        )
                    )
                )
            )
        )

        rule.activity.showFragment(FollowerListFragment())

        onView(withText("John Lennon")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(allOf(withParent(withChild(withText("John Lennon"))), withText("The Beatles F.C.")))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withText("Ringo Starr")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(allOf(withParent(withChild(withText("Ringo Starr"))), withText("The Beatles F.C.")))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}
