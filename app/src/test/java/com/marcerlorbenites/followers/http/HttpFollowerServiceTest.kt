package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Club
import com.marcerlorbenites.followers.Follower
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class HttpFollowerServiceTest {

    @Test
    fun `Given there is internet connection When get contacts is called Then Followers API is called with GET method`() {
        val server = MockWebServer()
        server.start()
        val baseUrl = server.url("/").toString()

        val followerList = listOf(
            Follower(
                "1",
                "John",
                "Lennon",
                "http://thebeatles.com/john",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "2",
                "Ringo",
                "Starr",
                "http://thebeatles.com/ringo",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "3",
                "George",
                "Harrison",
                "http://thebeatles.com/george",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "4",
                "Paul",
                "Mccartney",
                "http://thebeatles.com/paul",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")

            )
        )

        val httpClient = OkHttpClient()
        val service = HttpFollowerService(
            baseUrl,
            httpClient,
            FakeJsonConverter(followerList)
        )

        server.enqueue(MockResponse().setResponseCode(200).setBody("{}"))

        assertEquals(followerList, service.getFollowers())

        val request = server.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/Followers/db", request.path)
    }

    @Test
    fun `Given there is internet connection When get next contacts is called Then Followers API is called with GET method and Follower id as parameter`() {
        val server = MockWebServer()
        server.start()
        val baseUrl = server.url("/").toString()

        val followerList = listOf(
            Follower(
                "3",
                "George",
                "Harrison",
                "http://thebeatles.com/george",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")
            ),
            Follower(
                "4",
                "Paul",
                "Mccartney",
                "http://thebeatles.com/paul",
                Club("The Beatles F.C.", "http://thebeatles.com/logo")

            )
        )

        val httpClient = OkHttpClient()
        val service = HttpFollowerService(
            baseUrl,
            httpClient,
            FakeJsonConverter(followerList)
        )

        server.enqueue(MockResponse().setResponseCode(200).setBody("{}"))

        assertEquals(followerList, service.getNextFollowers("2"))

        val request = server.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/SecondPage/db", request.path)
    }

    @Test
    fun `Given a valid request When get contacts is called And a non successful response is received Then should raise an illegal state exception`() {
        val server = MockWebServer()
        server.start()
        val baseUrl = server.url("/").toString()

        val service = HttpFollowerService(
            baseUrl,
            OkHttpClient(),
            FakeJsonConverter()
        )

        server.enqueue(MockResponse().setResponseCode(500))

        try {
            service.getFollowers()
            Assert.fail("Should throw an illegal state exception")
        } catch (_: IllegalStateException) {
        }

        server.shutdown()
    }

    @Test
    fun `Given a valid request When get next contacts is called And a non successful response is received Then should raise an illegal state exception`() {
        val server = MockWebServer()
        server.start()
        val baseUrl = server.url("/").toString()

        val service = HttpFollowerService(
            baseUrl,
            OkHttpClient(),
            FakeJsonConverter()
        )

        server.enqueue(MockResponse().setResponseCode(500))

        try {
            service.getNextFollowers("1234")
            Assert.fail("Should throw an illegal state exception")
        } catch (_: IllegalStateException) {
        }

        server.shutdown()
    }
}
