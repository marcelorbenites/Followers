package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.Dispatcher
import com.marcerlorbenites.followers.state.ErrorFactory
import com.marcerlorbenites.followers.state.State

class PlayerFollowerManager(
    private val service: FollowerService,
    errorFactory: ErrorFactory<Error>,
    dispatcher: Dispatcher<Followers>,
    currentState: State<Followers, Error> = State(
        State.Name.IDLE
    )
) : FollowerManager(dispatcher, currentState, errorFactory) {

    override fun setup() {
        loadFollowers()
    }

    override fun loadFollowers() {
        load { Followers(service.getFollowers()) }
    }

    override fun loadMoreFollowers() {
        load {
            val followers = currentState.value!!

            if (followers.list.isNotEmpty()) {
                val nextFollowers = service.getNextFollowers(followers.last!!.id)
                val list = followers.list.toMutableList()
                list.addAll(nextFollowers)
                followers.copy(list = list)
            } else {
                followers
            }
        }
    }

    override fun selectFollower(followerId: String) {
        load {
            currentState.value!!.copy(selectedFollowerId = followerId)
        }
    }
}
