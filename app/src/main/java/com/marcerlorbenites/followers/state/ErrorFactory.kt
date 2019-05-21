package com.marcerlorbenites.followers.state

interface ErrorFactory<E> {
    fun create(throwable: Throwable): E
}
