package com.marcerlorbenites.followers.state

import com.marcerlorbenites.followers.Error
import com.marcerlorbenites.followers.FakeDispatcher
import com.marcerlorbenites.followers.FakeError
import com.marcerlorbenites.followers.FakeErrorFactory
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Test

class StateMachineTest {

    @Test
    fun `Given an idle state When state listener is registered and unregistered and state is moved to loaded Then should not call the state listener`() {
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)

        stateMachine.registerListener(listenerMock)
        stateMachine.unregisterListener(listenerMock)
        val value = TestValue()
        stateMachine.load {
            value
        }

        verify(exactly = 0) {
            listenerMock.onStateUpdate(State(State.Name.LOADING))
            listenerMock.onStateUpdate(State(State.Name.LOADED, value))
        }
    }

    @Test
    fun `Given an idle state When load is called Then current state should return loaded`() {
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val value = TestValue()
        val mockListener = mockk<StateListener<TestValue, Error>>(relaxed = true)
        stateMachine.registerListener(mockListener)
        stateMachine.load { value }

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADED, value))
        }
    }

    @Test
    fun `Given an loaded state When load is called And an error occurs Then current state should return error`() {
        val value = TestValue()
        val error = FakeError()
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(error),
            State(State.Name.LOADED, value),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue, Error>>(relaxed = true)
        stateMachine.registerListener(mockListener)

        stateMachine.load {
            throw IllegalStateException()
        }

        verify {
            mockListener.onStateUpdate(State(State.Name.ERROR, value, error))
        }
    }

    @Test
    fun `Given a loading state When load is called Then current state should return loading`() {
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.LOADING),
            mutableListOf()
        ) {}
        val mockListener = mockk<StateListener<TestValue, Error>>(relaxed = true)
        stateMachine.registerListener(mockListener)
        stateMachine.load { TestValue() }

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When load is called Then current state should return loading`() {
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val value = TestValue()
        val mockListener = mockk<StateListener<TestValue, Error>>(relaxed = true)
        stateMachine.registerListener(mockListener)
        stateMachine.load { value }

        verify {
            mockListener.onStateUpdate(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When two different listeners are registered and state is moved to loaded Then should call both listeners`() {
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)

        val secondListenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)
        stateMachine.registerListener(listenerMock)
        stateMachine.registerListener(secondListenerMock)
        val value = TestValue()
        stateMachine.load { value }

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
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)

        val secondListenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)

        stateMachine.registerListener(listenerMock)
        stateMachine.registerListener(secondListenerMock)
        stateMachine.unregisterListener(listenerMock)
        val value = TestValue()
        stateMachine.load { value }

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
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)

        stateMachine.registerListener(listenerMock)

        verify {
            listenerMock.onStateUpdate(State(State.Name.IDLE))
        }
    }

    @Test
    fun `Given an idle state and a registered listener When second listener registration Then should notify the second listener And should not notify the first listener a second time`() {
        val stateMachine = object : StateMachine<TestValue, Error>(
            FakeDispatcher(),
            FakeErrorFactory(),
            State(State.Name.IDLE),
            mutableListOf()
        ) {}
        val listenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)
        stateMachine.registerListener(listenerMock)

        val secondListenerMock = mockk<StateListener<TestValue, Error>>(relaxed = true)
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
