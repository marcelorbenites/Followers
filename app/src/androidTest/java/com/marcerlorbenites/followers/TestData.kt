package com.marcerlorbenites.followers

class TestData {

    companion object {
        const val FOLLOWERS_JSON = """
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
        """
    }
}
