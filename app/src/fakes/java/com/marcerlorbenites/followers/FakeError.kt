package com.marcerlorbenites.followers

class FakeError(private val network: Boolean = false): Error {
    override fun isNetwork() = network
}
