package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.rx.RxJavaDispatcher
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateListener
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class PlayerFollowerManagerTest {

    @Test
    fun `Given follower service returns a list of followers When setup is called Then emit loaded state And the list of followers`() {

        val followerList = listOf(
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
            ),
            Follower(
                "3",
                "George",
                "Harrison",
                "http://thebeatles.com/george",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "4",
                "Paul",
                "Mccartney",
                "http://thebeatles.com/paul",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            )
        )
        val manager = PlayerFollowerManager(
            FakeFollowerService(followerList),
            FakeErrorFactory(),
            FakeDispatcher()
        )

        val listenerMock = mockk<StateListener<Followers, Error>>(relaxed = true)
        manager.registerListener(listenerMock)
        manager.setup()

        verifyOrder {
            listenerMock.onStateUpdate(State(State.Name.IDLE))
            listenerMock.onStateUpdate(State(State.Name.LOADING))
            listenerMock.onStateUpdate(State(State.Name.LOADED, Followers(followerList)))
        }
    }

    @Test
    fun `Given follower service returns a list of followers When load followers is called Then emit loaded state And the list of followers`() {

        val followerList = listOf(
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
            ),
            Follower(
                "3",
                "George",
                "Harrison",
                "http://thebeatles.com/george",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "4",
                "Paul",
                "Mccartney",
                "http://thebeatles.com/paul",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            )
        )
        val manager = PlayerFollowerManager(
            FakeFollowerService(followerList),
            FakeErrorFactory(),
            FakeDispatcher()
        )

        val listenerMock = mockk<StateListener<Followers, Error>>(relaxed = true)
        manager.registerListener(listenerMock)
        manager.loadFollowers()

        verifyOrder {
            listenerMock.onStateUpdate(State(State.Name.IDLE))
            listenerMock.onStateUpdate(State(State.Name.LOADING))
            listenerMock.onStateUpdate(State(State.Name.LOADED, Followers(followerList)))
        }
    }

    @Test
    fun `Given follower service returns the next followers When load next followers Then emit list of followers plus the next followers`() {

        val allFollowers = listOf(
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
            ),
            Follower(
                "3",
                "George",
                "Harrison",
                "http://thebeatles.com/george",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "4",
                "Paul",
                "Mccartney",
                "http://thebeatles.com/paul",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            )
        )

        val firstFollowers = listOf(
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

        val nextFollowers = listOf(
            Follower(
                "3",
                "George",
                "Harrison",
                "http://thebeatles.com/george",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "4",
                "Paul",
                "Mccartney",
                "http://thebeatles.com/paul",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            )
        )

        val manager = PlayerFollowerManager(
            FakeFollowerService(firstFollowers, nextFollowers),
            FakeErrorFactory(),
            RxJavaDispatcher(Schedulers.trampoline(), Schedulers.trampoline())
        )

        val listenerMock = mockk<StateListener<Followers, Error>>(relaxed = true)
        manager.registerListener(listenerMock)
        manager.setup()

        manager.loadMoreFollowers()

        verifyOrder {
            listenerMock.onStateUpdate(State(State.Name.LOADED, Followers(firstFollowers)))
            listenerMock.onStateUpdate(State(State.Name.LOADING, Followers(firstFollowers)))
            listenerMock.onStateUpdate(State(State.Name.LOADED, Followers(allFollowers)))
        }
    }

    @Test
    fun `Given loaded list of followers When select is called with id Then emit the followers selected follower id`() {

        val list = listOf(
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

        val manager = PlayerFollowerManager(
            FakeFollowerService(),
            FakeErrorFactory(),
            FakeDispatcher(),
            State(State.Name.LOADED, Followers(list))
        )

        val listenerMock = mockk<StateListener<Followers, Error>>(relaxed = true)
        manager.registerListener(listenerMock)
        manager.selectFollower("1")

        verify {
            listenerMock.onStateUpdate(State(State.Name.LOADED, Followers(list, "1")))
        }
    }
}
