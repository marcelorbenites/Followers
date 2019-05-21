package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.ErrorFactory

class FakeErrorFactory(private val error: FakeError? = null): ErrorFactory<Error> {
    override fun create(throwable: Throwable) = error!!
}
