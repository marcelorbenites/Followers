package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.ErrorFactory
import com.marcerlorbenites.followers.state.State

class FakeFollowerManager(
    currentState: State<Followers, Error> = State(State.Name.IDLE),
    errorFactory: ErrorFactory<Error> = FakeErrorFactory()
) : FollowerManager(FakeDispatcher(), currentState, errorFactory) {
    override fun setup() {
    }

    override fun loadMoreFollowers() {
    }

    override fun loadFollowers() {
    }

    override fun selectFollower(followerId: String) {
    }
}
