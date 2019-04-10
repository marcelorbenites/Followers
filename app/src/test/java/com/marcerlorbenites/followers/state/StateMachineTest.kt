package com.marcerlorbenites.followers.state

import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class StateMachineTest {

    @Test
    fun `Given an idle state When state listener is registered and unregistered and state is moved to loading Then should not call the state listener`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        stateMachine.registerListener(listenerMock)
        stateMachine.unregisterListener(listenerMock)
        stateMachine.moveToLoading()

        verify(exactly = 0) {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When state is moved to loading with no value Then current state should return loading with no value`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)

        stateMachine.moveToLoading()

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADING, null))
        }
    }

    @Test
    fun `Given an idle state When state is moved to loaded with value Then current state should return loaded with value`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val value = TestValue()
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)

        stateMachine.moveToLoaded(value)

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADED, value))
        }
    }

    @Test
    fun `Given a loaded state with value When state is moved to loaded with no value Then current state should return loaded with current value`() {
        val currentValue = TestValue()
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.LOADED, currentValue),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)
        stateMachine.moveToLoaded()

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADED, currentValue))
        }
    }

    @Test
    fun `Given an idle state When state is moved to error Then current state should return error with current value`() {
        val value = TestValue()
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE, value),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)

        stateMachine.moveToError()

        verify {
            mockListener.onStateUpdate(State(State.Name.ERROR, value))
        }
    }

    @Test
    fun `Given a loading state When state is moved to idle Then current state should return idle with current value`() {
        val value = TestValue()
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.LOADING, value),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)

        stateMachine.moveToIdle()

        verify {
            mockListener.onStateUpdate(State(State.Name.IDLE, value))
        }
    }

    @Test
    fun `Given an idle state When state is moved to loading with value Then current state should return loading with value`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val value = TestValue()
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)

        stateMachine.moveToLoading(value)

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADING, value))
        }
    }

    @Test
    fun `Given an idle state When two different listeners are registered and state is moved to loading Then should call both listeners`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        val secondListenerMock = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(listenerMock)
        stateMachine.registerListener(secondListenerMock)
        stateMachine.moveToLoading()

        verify {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
        }

        verify {
            secondListenerMock.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When two different listeners are registered and the first is unregistered and state is moved to loading Then should call only the second listener`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        val secondListenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        stateMachine.registerListener(listenerMock)
        stateMachine.registerListener(secondListenerMock)
        stateMachine.unregisterListener(listenerMock)
        stateMachine.moveToLoading()

        verify(exactly = 0) {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
        }

        verify {
            secondListenerMock.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When listener registered Then should return idle status`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        stateMachine.registerListener(listenerMock)

        verify {
            listenerMock.onStateUpdate(State(State.Name.IDLE))
        }
    }

    @Test
    fun `Given an idle state and a registered listener When second listener registration Then should notify the second listener And should not notify the first listener a second time`() {
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(listenerMock)

        val secondListenerMock = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(secondListenerMock)

        verify {
            secondListenerMock.onStateUpdate(State(State.Name.IDLE))
        }

        verify(exactly = 1) {
            listenerMock.onStateUpdate(State(State.Name.IDLE))
        }
    }

    @Test
    fun `Given a loaded state When emit current state is request Then should return the same state`() {
        val value = TestValue()
        val stateMachine = object : StateMachine<TestValue>(
            State(State.Name.LOADED, value),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(listenerMock)

        stateMachine.emitCurrentState()

        verify(exactly = 2) {
            listenerMock.onStateUpdate(State(State.Name.LOADED, value))
            listenerMock.onStateUpdate(State(State.Name.LOADED, value))
        }
    }
}

class TestValue
