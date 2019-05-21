package com.marcerlorbenites.followers

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class ServiceErrorTest {

    @Test
    fun `Given an IO exception When is network is checked Then return true`() {
        val error = ServiceError(IOException())
        assertEquals(true, error.isNetwork())
    }

    @Test
    fun `Given an illegal state exception When is network is checked Then return false`() {
        val error = ServiceError(IllegalStateException())
        assertEquals(false, error.isNetwork())
    }
}
