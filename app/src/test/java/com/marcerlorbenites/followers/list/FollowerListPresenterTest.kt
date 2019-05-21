package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.Club
import com.marcerlorbenites.followers.FakeError
import com.marcerlorbenites.followers.Follower
import com.marcerlorbenites.followers.Followers
import com.marcerlorbenites.followers.state.State
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FollowerListPresenterTest {
    @Test
    fun `Given followers are loaded When state is updated Then show followers And hide main loading`() {

        val viewMock = mockk<FollowerListView>(relaxed = true)
        val presenter = FollowerListPresenter(viewMock, "No Club")
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
            viewMock.showFollowers(FollowerListViewModel(followers, "No Club"))
            viewMock.hideMainLoading()
            viewMock.hideListLoading()
            viewMock.hideError()
            viewMock.hideRetry()
        }
    }

    @Test
    fun `Given followers are loading And there are no followers When state is updated Then show main loading`() {

        val viewMock = mockk<FollowerListView>(relaxed = true)
        val presenter = FollowerListPresenter(viewMock, "No Club")

        presenter.onStateUpdate(
            State(State.Name.LOADING)
        )


        verify {
            viewMock.hideFollowers()
            viewMock.showMainLoading()
            viewMock.hideListLoading()
            viewMock.hideError()
            viewMock.hideRetry()
        }
    }

    @Test
    fun `Given followers are loading And there are followers When state is updated Then show list loading`() {

        val viewMock = mockk<FollowerListView>(relaxed = true)
        val presenter = FollowerListPresenter(viewMock, "No Club")
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
                State.Name.LOADING, followers
            )
        )

        verify {
            viewMock.hideMainLoading()
            viewMock.showListLoading()
            viewMock.hideError()
            viewMock.hideRetry()
        }
    }

    @Test
    fun `Given a network error loading followers When state is updated Then show network error And retry button`() {

        val viewMock = mockk<FollowerListView>(relaxed = true)
        val presenter = FollowerListPresenter(viewMock, "No Club")

        presenter.onStateUpdate(
            State(State.Name.ERROR, error = FakeError(true))
        )

        verify {
            viewMock.hideFollowers()
            viewMock.hideMainLoading()
            viewMock.hideListLoading()
            viewMock.showNetworkError()
            viewMock.showRetry()
        }
    }

    @Test
    fun `Given a unknown error loading followers When state is updated Then show unknown error And retry button`() {

        val viewMock = mockk<FollowerListView>(relaxed = true)
        val presenter = FollowerListPresenter(viewMock, "No Club")

        presenter.onStateUpdate(
            State(State.Name.ERROR, error = FakeError(false))
        )

        verify {
            viewMock.hideFollowers()
            viewMock.hideMainLoading()
            viewMock.hideListLoading()
            viewMock.showUnknownError()
            viewMock.showRetry()
        }
    }
}
