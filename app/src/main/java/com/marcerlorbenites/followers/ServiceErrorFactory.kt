package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.ErrorFactory

class ServiceErrorFactory : ErrorFactory<Error> {
    override fun create(throwable: Throwable) = ServiceError(throwable)
}
