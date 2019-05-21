package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.Club
import com.marcerlorbenites.followers.Follower
import org.junit.Assert.assertEquals
import org.junit.Test

class FollowerItemViewModelTest {

    @Test
    fun `Given follower has club When club name is requested Then return club name`() {
        val viewModel = FollowerItemViewModel(
            Follower(
                "1",
                "John",
                "Lennon",
                "http://thebeatles.com/john",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            "No Club"
        )

        assertEquals("The Beatles F.C.", viewModel.clubName)
    }

    @Test
    fun `Given follower has no club When club name is requested Then return no club text`() {
        val viewModel = FollowerItemViewModel(
            Follower(
                "1",
                "John",
                "Lennon",
                "http://thebeatles.com/john"
            ),
            "No Club"
        )

        assertEquals("No Club", viewModel.clubName)
    }
}
