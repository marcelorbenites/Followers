package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.Dispatcher
import com.marcerlorbenites.followers.state.State

class PlayerFollowerManager(
    private val service: FollowerService,
    dispatcher: Dispatcher<Followers>,
    currentState: State<Followers> = State(State.Name.IDLE)
) : FollowerManager(dispatcher, currentState) {

    override fun setup() {
        loadFollowers()
    }

    override fun loadFollowers() {
        moveToLoaded { Followers(service.getFollowers()) }
    }

    override fun loadMoreFollowers() {
        moveToLoaded {
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
        moveToLoaded {
            currentState.value!!.copy(selectedFollowerId = followerId)
        }
    }
}
