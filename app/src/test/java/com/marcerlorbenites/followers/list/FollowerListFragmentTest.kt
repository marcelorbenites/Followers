package com.marcerlorbenites.followers.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marcerlorbenites.followers.*
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
    fun `Given followers are loaded When view is resumed Then show followers And hide main loading`() {

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
                            Club("The Beatles F.C.", "http://thebeatles.com/logo")
                        ),
                        Follower(
                            "2",
                            "Ringo",
                            "Starr",
                            "http://thebeatles.com/ringo",
                            Club("The Beatles F.C.", "http://thebeatles.com/logo")
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
        onView(withId(R.id.mainLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.listLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.errorText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.retryButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun `Given followers are loading And there are no followers When view is resumed Then show main loading`() {

        rule.activity.testFollowerManager = FakeFollowerManager(State(State.Name.LOADING))
        rule.activity.showFragment(FollowerListFragment())

        onView(withId(R.id.followerList)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.mainLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.listLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.errorText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.retryButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun `Given followers are loading And there are followers When view is resumed Then show list loading`() {

        rule.activity.testFollowerManager = FakeFollowerManager(
            State(
                State.Name.LOADING, Followers(
                    listOf(
                        Follower(
                            "1",
                            "John",
                            "Lennon",
                            "http://thebeatles.com/john",
                            Club("The Beatles F.C.", "http://thebeatles.com/logo")
                        ),
                        Follower(
                            "2",
                            "Ringo",
                            "Starr",
                            "http://thebeatles.com/ringo",
                            Club("The Beatles F.C.", "http://thebeatles.com/logo")
                        )
                    )
                )
            )
        )
        rule.activity.showFragment(FollowerListFragment())

        onView(withId(R.id.mainLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.listLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.errorText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.retryButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun `Given an error loading followers When view is resumed Then show error And retry button`() {

        rule.activity.testFollowerManager = FakeFollowerManager(State(State.Name.ERROR))
        rule.activity.showFragment(FollowerListFragment())

        onView(withId(R.id.followerList)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.mainLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.listLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.errorText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.retryButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}
