package com.marcerlorbenites.followers.state

abstract class StateMachine<T>(
    protected var currentState: State<T>,
    private val listeners: MutableList<StateListener<T>> = mutableListOf()
) {

    fun registerListener(listener: StateListener<T>) {
        this.listeners.add(listener)
        listener.onStateUpdate(currentState)
    }

    fun unregisterListener(listener: StateListener<T>) {
        this.listeners.remove(listener)
    }

    fun moveToLoading(value: T? = null) {
        updateState(
            State(
                State.Name.LOADING,
                value ?: currentState.value
            )
        )
    }

    fun moveToLoaded(value: T? = null) {
        updateState(
            State(
                State.Name.LOADED,
                value ?: currentState.value
            )
        )
    }

    fun moveToError() {
        updateState(
            State(
                State.Name.ERROR,
                currentState.value
            )
        )
    }

    fun moveToIdle() {
        updateState(
            State(
                State.Name.IDLE,
                currentState.value
            )
        )
    }

    fun emitCurrentState() {
        updateState(currentState)
    }

    private fun updateState(state: State<T>) {
        currentState = state
        for (listener in listeners) {
            listener.onStateUpdate(currentState)
        }
    }
}
