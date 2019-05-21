package com.marcerlorbenites.followers.detail

import com.marcerlorbenites.followers.Club
import com.marcerlorbenites.followers.Follower
import com.marcerlorbenites.followers.Followers
import com.marcerlorbenites.followers.navigator.FragmentNavigator
import com.marcerlorbenites.followers.state.State
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FollowerDetailPresenterTest {

    @Test
    fun `Given no follower is selected When state updated Then navigate back`() {
        val viewMock = mockk<FollowerDetailView>(relaxed = true)
        val navigatorMock = mockk<FragmentNavigator>(relaxed = true)
        val presenter = FollowerDetailPresenter(viewMock, navigatorMock)

        val followers = Followers(
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

        presenter.onStateUpdate(
            State(
                State.Name.LOADED,
                followers
            )
        )

        verify {
            navigatorMock.navigateBack()
        }
    }

    @Test
    fun `Given follower is selected And follower has club When state updated Then show selected follower name And profile picture And club picture`() {
        val viewMock = mockk<FollowerDetailView>(relaxed = true)
        val navigatorMock = mockk<FragmentNavigator>(relaxed = true)
        val presenter = FollowerDetailPresenter(viewMock, navigatorMock)

        val followers = Followers(
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
            ),
            "2"
        )

        presenter.onStateUpdate(
            State(
                State.Name.LOADED,
                followers
            )
        )

        verify {
            viewMock.showName("Ringo Starr")
            viewMock.showProfilePicture("http://thebeatles.com/ringo")
            viewMock.showClubPicture("http://thebeatles.com/logo")
        }
    }

    @Test
    fun `Given follower is selected And follower has no club When state updated Then show selected follower name And profile picture`() {
        val viewMock = mockk<FollowerDetailView>(relaxed = true)
        val navigatorMock = mockk<FragmentNavigator>(relaxed = true)
        val presenter = FollowerDetailPresenter(viewMock, navigatorMock)

        val followers = Followers(
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
                    "http://thebeatles.com/ringo"
                )
            ),
            "2"
        )

        presenter.onStateUpdate(
            State(
                State.Name.LOADED,
                followers
            )
        )

        verify {
            viewMock.showName("Ringo Starr")
            viewMock.showProfilePicture("http://thebeatles.com/ringo")
        }
    }
}
