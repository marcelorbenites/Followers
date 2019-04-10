package com.marcerlorbenites.followers.state

interface StateListener<T> {
    fun onStateUpdate(state: State<T>)
}
