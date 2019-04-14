package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.Dispatcher

class FakeDispatcher<T> : Dispatcher<T> {
    override fun dispatch(function: () -> T, success: (T) -> Unit, error: (Throwable) -> Unit) {
        try {
            val result = function.invoke()
            success.invoke(result)
        } catch (e: Throwable) {
            error.invoke(e)
        }
    }

    override fun dispatch(function: () -> Unit, success: () -> Unit, error: (Throwable) -> Unit) {
        try {
            function.invoke()
            success.invoke()
        } catch (e: Throwable) {
            error.invoke(e)
        }
    }
}

