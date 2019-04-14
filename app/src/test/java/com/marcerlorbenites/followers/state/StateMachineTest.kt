package com.marcerlorbenites.followers.state

import com.marcerlorbenites.followers.FakeDispatcher
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Test

class StateMachineTest {

    @Test
    fun `Given an idle state When state listener is registered and unregistered and state is moved to loaded Then should not call the state listener`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        stateMachine.registerListener(listenerMock)
        stateMachine.unregisterListener(listenerMock)
        val value = TestValue()
        stateMachine.moveToLoaded {
            value
        }

        verify(exactly = 0) {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
            listenerMock.onStateUpdate(State(State.Name.LOADED, value))
        }
    }

    @Test
    fun `Given an idle state When state is moved to idle Then should current state should return idle`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        stateMachine.registerListener(listenerMock)
        stateMachine.unregisterListener(listenerMock)
        stateMachine.moveToIdle {  }

        verify {
            listenerMock.onStateUpdate(State(State.Name.IDLE))
        }

        verify(exactly = 0) {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When state is moved to loaded Then current state should return loaded`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val value = TestValue()
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)
        stateMachine.moveToLoaded { value }

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADED, value))
        }
    }

    @Test
    fun `Given an loaded state When state is moved loaded And an error occurs Then current state should return error`() {
        val value = TestValue()
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.LOADED, value),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)

        stateMachine.moveToLoaded {
            throw IllegalStateException()
        }

        verify {
            mockListener.onStateUpdate(State(State.Name.ERROR, value))
        }
    }

    @Test
    fun `Given an idle state When state is moved to error Then current state should return error with current value`() {
        val value = TestValue()
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
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
    fun `Given a loading state When state is moved to loaded Then current state should return loading`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.LOADING),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)
        stateMachine.moveToLoaded { TestValue() }

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When state is moved to loaded Then current state should return loading`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val value = TestValue()
        val mockListener = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(mockListener)
        stateMachine.moveToLoaded { value }

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When two different listeners are registered and state is moved to loaded Then should call both listeners`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        val secondListenerMock = mockk<StateListener<TestValue>>(relaxed = true)
        stateMachine.registerListener(listenerMock)
        stateMachine.registerListener(secondListenerMock)
        val value = TestValue()
        stateMachine.moveToLoaded { value }

        verifyOrder {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
            listenerMock.onStateUpdate(State(State.Name.LOADED, value))
        }

        verifyOrder {
            secondListenerMock.onStateUpdate(State(State.Name.LOADING))
            secondListenerMock.onStateUpdate(State(State.Name.LOADED, value))
        }
    }

    @Test
    fun `Given an idle state When two different listeners are registered and the first is unregistered and state is moved to loaded Then should call only the second listener`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        val secondListenerMock = mockk<StateListener<TestValue>>(relaxed = true)

        stateMachine.registerListener(listenerMock)
        stateMachine.registerListener(secondListenerMock)
        stateMachine.unregisterListener(listenerMock)
        val value = TestValue()
        stateMachine.moveToLoaded { value }

        verify(exactly = 0) {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
            listenerMock.onStateUpdate(State(State.Name.LOADED, value))
        }

        verifyOrder {
            secondListenerMock.onStateUpdate(State(State.Name.LOADING))
            secondListenerMock.onStateUpdate(State(State.Name.LOADED, value))
        }
    }

    @Test
    fun `Given an idle state When listener registered Then should return idle status`() {
        val stateMachine = object : StateMachine<TestValue>(
            FakeDispatcher(),
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
            FakeDispatcher(),
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
}

class TestValue
