package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.State

class FakeFollowerManager(
    currentState: State<Followers> = State(State.Name.IDLE)
) : FollowerManager(currentState) {
    override fun setup() {
    }
    override fun loadMoreFollowers() {
    }
    override fun loadFollowers() {
    }
}
