package com.marcerlorbenites.followers.http

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcerlorbenites.followers.Club
import com.marcerlorbenites.followers.Follower
import com.marcerlorbenites.followers.TestApplication
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class)
class JsonObjectConverterTest {

    @Test
    fun `Given a followers json When parse is called Then should return a list of followers`() {
        val json = """
            {
               "response":[
                  {
                     "slug":"1",
                     "firstname":"John",
                     "lastname":"Lennon",
                     "profile_picture":"http://thebeatles.com/john",
                     "team":{
                        "league":{
                           "name":"Barclays Premier League"
                        },
                        "club":{
                           "name":"The Beatles F.C.",
                           "logo_url":"http://thebeatles.com/logo"
                        }
                     }
                  },
                  {
                     "slug":"2",
                     "firstname":"Ringo",
                     "lastname":"Starr",
                     "profile_picture":"http://thebeatles.com/ringo",
                     "team":{
                        "league":{
                           "name":"Barclays Premier League"
                        },
                        "club":{
                           "name":"The Beatles F.C.",
                           "logo_url":"http://thebeatles.com/logo"
                        }
                     }
                  }
                ]
            }
        """.trimIndent()
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
            )
        )
        val jsonConverter = JsonObjectConverter()
        assertEquals(followerList, jsonConverter.parseFollowers(json))
    }

}
