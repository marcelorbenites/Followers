package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.Dispatcher
import com.marcerlorbenites.followers.state.ErrorFactory
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateMachine

abstract class FollowerManager(
    dispatcher: Dispatcher<Followers>,
    currentState: State<Followers, Error> = State(
        State.Name.IDLE
    ),
    errorFactory: ErrorFactory<Error>
) : StateMachine<Followers, Error>(dispatcher, errorFactory, currentState) {
    abstract fun setup()
    abstract fun loadMoreFollowers()
    abstract fun loadFollowers()
    abstract fun selectFollower(followerId: String)
}
