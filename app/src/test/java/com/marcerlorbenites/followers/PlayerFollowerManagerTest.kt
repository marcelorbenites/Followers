package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateListener
import io.mockk.mockk
import io.mockk.verifyOrder
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class PlayerFollowerManagerTest {

    @Test
    fun `Given follower service returns a list of followers When setup is called Then emit loaded state And the list of followers`() {

        val followerList = listOf(
            Follower("John Lennon", "The Beatles", "Barclays Premier League"),
            Follower("Ringo Starr", "The Beatles", "Barclays Premier League"),
            Follower("George Harrison", "The Beatles", "Barclays Premier League"),
            Follower("Paul Mccartney", "The Beatles", "Barclays Premier League")
        )
        val manager = PlayerFollowerManager(
            FakeFollowerService(followerList),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        val listenerMock = mockk<StateListener<Followers>>(relaxed = true)
        manager.registerListener(listenerMock)
        manager.setup()

        verifyOrder {
            listenerMock.onStateUpdate(State(State.Name.IDLE))
            listenerMock.onStateUpdate(State(State.Name.LOADING))
            listenerMock.onStateUpdate(State(State.Name.LOADED, Followers(followerList)))
        }
    }
}
