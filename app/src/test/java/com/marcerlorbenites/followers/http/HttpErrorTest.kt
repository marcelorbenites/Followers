package com.marcerlorbenites.followers.http

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class HttpErrorTest {

    @Test
    fun `Given an IO exception When is network is checked Then return true`() {
        val error = HttpError(IOException())
        assertEquals(true, error.isNetwork())
    }

    @Test
    fun `Given an illegal state exception When is network is checked Then return false`() {
        val error = HttpError(IllegalStateException())
        assertEquals(false, error.isNetwork())
    }
}
