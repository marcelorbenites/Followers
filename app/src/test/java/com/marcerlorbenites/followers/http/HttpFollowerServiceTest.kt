package com.marcerlorbenites.followers.http

import com.marcerlorbenites.followers.Club
import com.marcerlorbenites.followers.Follower
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

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
                Club("The Beatles F.C.", "http://thebeatles.com/logo"),
                "Barclays Premier League"
            ),
            Follower(
                "2",
                "Ringo",
                "Starr",
                "http://thebeatles.com/ringo",
                Club("The Beatles F.C.", "http://thebeatles.com/logo"),
                "Barclays Premier League"
            ),
            Follower(
                "3",
                "George",
                "Harrison",
                "http://thebeatles.com/george",
                Club("The Beatles F.C.", "http://thebeatles.com/logo"),
                "Barclays Premier League"
            ),
            Follower(
                "4",
                "Paul",
                "Mccartney",
                "http://thebeatles.com/paul",
                Club("The Beatles F.C.", "http://thebeatles.com/logo"),
                "Barclays Premier League"
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
        assertEquals("/followers", request.path)
    }

    @Test
    fun `Given a valid request When get contacts is called And a non successful response is received Then should raise an IO exception`() {
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
            Assert.fail("Should throw an IO Exception")
        } catch (_: IOException) {
        }

        server.shutdown()
    }
}
