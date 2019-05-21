package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Error
import com.marcerlorbenites.followers.state.ErrorFactory

class HttpErrorFactory : ErrorFactory<Error> {
    override fun create(throwable: Throwable) = HttpError(throwable)
}
