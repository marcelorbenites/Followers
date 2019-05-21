package com.marcerlorbenites.followers.state

abstract class StateMachine<T, E>(
    private val dispatcher: Dispatcher<T>,
    private val errorFactory: ErrorFactory<E>,
    protected var currentState: State<T, E> = State(State.Name.IDLE),
    private val listeners: MutableList<StateListener<T, E>> = mutableListOf()
) {

    fun registerListener(listener: StateListener<T, E>) {
        this.listeners.add(listener)
        listener.onStateUpdate(currentState)
    }

    fun unregisterListener(listener: StateListener<T, E>) {
        this.listeners.remove(listener)
    }

    fun load(function: () -> T) {
        if (currentState.name != State.Name.LOADING) {
            updateState(State(State.Name.LOADING, currentState.value))
            dispatcher.dispatch(function, { value ->
                updateState(State(State.Name.LOADED, value))
            }, {
                moveToError(it)
            })
        } else {
            updateState(currentState)
        }
    }

    private fun moveToError(throwable: Throwable) {
        updateState(State(State.Name.ERROR, currentState.value, errorFactory.create(throwable)))
    }

    private fun updateState(state: State<T, E>) {
        currentState = state
        for (listener in listeners) {
            listener.onStateUpdate(currentState)
        }
    }
}
