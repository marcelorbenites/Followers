package com.marcerlorbenites.followers.state

interface Dispatcher<T> {
    fun dispatch(function: () -> T, success: (T) -> Unit, error: (Throwable) -> Unit)

    fun dispatch(function: () -> Unit, success: () -> Unit, error: (Throwable) -> Unit)
}
