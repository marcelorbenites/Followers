package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.FollowerManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FollowerListScrollControllerTest {

    @Test
    fun `Given can scroll down And last visible position is within offset When on scroll Then load more followers`() {
        val followerManagerMock = mockk<FollowerManager>(relaxed = true)
        val scrollController = FollowerListScrollController(followerManagerMock, 5)

        scrollController.onScroll(true, 20, 16)

        verify {
            followerManagerMock.loadMoreFollowers()
        }
    }

    @Test
    fun `Given can scroll down And last visible position is not within offset When on scroll Then do not load more followers`() {
        val followerManagerMock = mockk<FollowerManager>(relaxed = true)
        val scrollController = FollowerListScrollController(followerManagerMock, 5)

        scrollController.onScroll(true, 20, 10)

        verify(exactly = 0) {
            followerManagerMock.loadMoreFollowers()
        }
    }

    @Test
    fun `Given can not scroll down When on scroll Then do not load more followers`() {
        val followerManagerMock = mockk<FollowerManager>(relaxed = true)
        val scrollController = FollowerListScrollController(followerManagerMock, 5)

        scrollController.onScroll(false, 20, 2)

        verify(exactly = 0) {
            followerManagerMock.loadMoreFollowers()
        }
    }
}
