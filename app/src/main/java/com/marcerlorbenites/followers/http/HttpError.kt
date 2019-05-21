package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Error
import java.io.IOException

class HttpError(private val throwable: Throwable): Error {
    override fun isNetwork()= throwable is IOException
}
