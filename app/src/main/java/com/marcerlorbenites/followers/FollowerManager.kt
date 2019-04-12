package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateMachine

abstract class FollowerManager(currentState: State<Followers> = State(State.Name.IDLE)) :
    StateMachine<Followers>(currentState) {
    abstract fun setup()
    abstract fun loadMoreFollowers()
    abstract fun loadFollowers()
}
