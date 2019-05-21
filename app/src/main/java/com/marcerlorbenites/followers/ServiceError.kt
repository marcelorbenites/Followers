package com.marcerlorbenites.followers

import java.io.IOException

class ServiceError(private val throwable: Throwable) : Error {
    override fun isNetwork() = throwable is IOException
}
