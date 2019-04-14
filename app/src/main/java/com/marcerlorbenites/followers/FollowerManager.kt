package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.Dispatcher
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateMachine

abstract class FollowerManager(
    dispatcher: Dispatcher<Followers>,
    currentState: State<Followers> = State(State.Name.IDLE)
) :
    StateMachine<Followers>(dispatcher, currentState) {
    abstract fun setup()
    abstract fun loadMoreFollowers()
    abstract fun loadFollowers()
    abstract fun selectFollower(followerId: String)
}
