package com.marcerlorbenites.followers.state

data class State<T>(val name: Name, val value: T? = null) {
    enum class Name {
        IDLE,
        LOADING,
        LOADED,
        ERROR
    }
}
