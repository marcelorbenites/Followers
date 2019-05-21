package com.marcerlorbenites.followers.state

interface StateListener<T, E> {
    fun onStateUpdate(state: State<T, E>)
}
