package com.marcerlorbenites.followers.state

abstract class StateMachine<T>(
    private val dispatcher: Dispatcher<T>,
    protected var currentState: State<T> = State(State.Name.IDLE),
    private val listeners: MutableList<StateListener<T>> = mutableListOf()
) {

    fun registerListener(listener: StateListener<T>) {
        this.listeners.add(listener)
        listener.onStateUpdate(currentState)
    }

    fun unregisterListener(listener: StateListener<T>) {
        this.listeners.remove(listener)
    }

    fun moveToLoaded(function: () -> T) {
        if (currentState.name != State.Name.LOADING) {
            updateState(State(State.Name.LOADING, currentState.value))
            dispatcher.dispatch(function, { value ->
                updateState(State(State.Name.LOADED, value))
            }, {
                moveToError()
            })
        } else {
            updateState(currentState)
        }
    }

    fun moveToIdle(function: () -> Unit) {
        if (currentState.name != State.Name.IDLE) {
            updateState(State(State.Name.LOADING, currentState.value))
            dispatcher.dispatch(function, {
                updateState(State(State.Name.IDLE, currentState.value))
            }, {
                moveToError()
            })
        } else {
            updateState(currentState)
        }
    }

    fun moveToError() {
        updateState(State(State.Name.ERROR, currentState.value))
    }

    private fun updateState(state: State<T>) {
        currentState = state
        for (listener in listeners) {
            listener.onStateUpdate(currentState)
        }
    }
}
