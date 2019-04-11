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
                     "club":{
                       "name":"The Beatles F.C.",
                       "logo_url":"http://thebeatles.com/logo"
                     }
                  },
                  {
                     "slug":"2",
                     "firstname":"Ringo",
                     "lastname":"Starr",
                     "profile_picture":"http://thebeatles.com/ringo",
                     "club":{
                       "name":"The Beatles F.C.",
                       "logo_url":"http://thebeatles.com/logo"
                     }
                  }
                ]
            }
        """

        const val FIRST_FOLLOWERS_JSON = """
            {
           "response":[
              {
                 "slug":"cra-alex",
                 "firstname":"",
                 "lastname":"CRA Alex",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/cra-alex\/profile_picture\/f603d29626dbfd41c7177dea78d17a42",
                 "club":{
                    "slug":"urania-lutgendortmund",
                    "name":"Urania Lütgendortmund",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/urania-lutgendortmund\/logo"
                 }
              },
              {
                 "slug":"47d8bc02-ed1b-471b-a516-39e137c21c00",
                 "firstname":"",
                 "lastname":"lzt",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/47d8bc02-ed1b-471b-a516-39e137c21c00\/profile_picture\/0301ebb4-d3f8-4b0a-a6d7-8452a20e506b",
                 "club":{
                    "slug":"ent-s-courcelles-sur-nied",
                    "name":"ENT.S. COURCELLES SUR NIED",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/ent-s-courcelles-sur-nied\/logo"
                 }
              },
              {
                 "slug":"thatonekid",
                 "firstname":"",
                 "lastname":"thatonekid",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/thatonekid\/profile_picture\/2bbe0420-13ec-483c-8f96-a977796740ef",
                 "club":{
                    "slug":"aarup-bk",
                    "name":"Aarup BK",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/aarup-bk\/logo"
                 }
              },
              {
                 "slug":"zolo-1536749638",
                 "firstname":"",
                 "lastname":"ZoLo",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/zolo-1536749638\/profile_picture\/c8e7e7a0-ca2a-41d0-9e0e-ea7d364c9cf9",
                 "club":{
                    "slug":"f-c-o-viassois",
                    "name":"F. C. O. VIASSOIS",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/f-c-o-viassois\/logo"
                 }
              },
              {
                 "slug":"aaron-elbaz",
                 "firstname":"Aaron",
                 "lastname":"Elbaz",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/aaron-elbaz\/profile_picture\/c5bcf977-6f08-4c8e-adfb-115913c09cf5",
                 "club":{
                    "slug":null,
                    "name":null,
                    "logo_url":null
                 }
              },
              {
                 "slug":"aaron-florincamagna-1538938137-a0e82d0dcc",
                 "firstname":"Aaron",
                 "lastname":"FlorinCamagna",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/aaron-florincamagna-1538938137-a0e82d0dcc\/profile_picture\/89d621b2c757ee3858b4debd7fa99268",
                 "club":{
                    "slug":"metallo-s-chantenay-nantes",
                    "name":"METALLO S. CHANTENAY NANTES",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/metallo-s-chantenay-nantes\/logo"
                 }
              },
              {
                 "slug":"abakar-mahamat-1540318988-81365c00e6",
                 "firstname":"Abakar",
                 "lastname":"Mahamat",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/abakar-mahamat-1540318988-81365c00e6\/profile_picture\/79ffea6e-c11a-4702-8d4f-d43ab8f668dd",
                 "club":{
                    "slug":"f-c-st-baldoph",
                    "name":"F.C. ST BALDOPH",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/f-c-st-baldoph\/logo"
                 }
              },
              {
                 "slug":"arssen-rango-1537991497-bc5187cd7b",
                 "firstname":"Abdallah",
                 "lastname":"Houmadi",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"a-s-de-caissargues",
                    "name":"A.S. DE CAISSARGUES",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/a-s-de-caissargues\/logo"
                 }
              },
              {
                 "slug":"abdallah-sy-savane-1538067160-dd1767560a",
                 "firstname":"Abdallah",
                 "lastname":"Sy Savane",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/abdallah-sy-savane-1538067160-dd1767560a\/profile-picture\/A91ADD3B-6804-4173-A664-505C0D80C62F.jpeg",
                 "club":{
                    "slug":"u-s-municipale-saran",
                    "name":"U.S. MUNICIPALE SARAN",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/u-s-municipale-saran\/logo"
                 }
              },
              {
                 "slug":"abuibui-madi",
                 "firstname":"Abdal-Malik Kassim ",
                 "lastname":"Madi",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"pamandzi-s-c",
                    "name":"PAMANDZI S.C.",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/pamandzi-s-c\/logo"
                 }
              }
           ]
        }
        """

        const val SECOND_FOLLOWERS_JSON = """
            {
            "response":[
             {
                 "slug":"abdelaat-marocc",
                 "firstname":"Abdelaat",
                 "lastname":"Ifkharne",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"asko-ebelsberg-linz",
                    "name":"ASKÖ Ebelsberg Linz",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/asko-ebelsberg-linz\/logo_cd66eee70232"
                 }
              },
              {
                 "slug":"kader-ghalem-1537728914",
                 "firstname":"abdelkader",
                 "lastname":"Ghalem",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"nimes-lasallien",
                    "name":"NIMES LASALLIEN",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/nimes-lasallien\/logo"
                 }
              },
              {
                 "slug":"1b90363c-0593-4adf-a652-8612ff6f1ce2",
                 "firstname":"Abdel Latif",
                 "lastname":"Zendjina",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"bayern-alzenau",
                    "name":"Bayern Alzenau",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/bayern-alzenau\/logo"
                 }
              },
              {
                 "slug":"abder-rooney-1537490914",
                 "firstname":"Abder",
                 "lastname":"Rooney",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"villetaneuse-c-s",
                    "name":"VILLETANEUSE C.S.",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/villetaneuse-c-s\/logo"
                 }
              },
              {
                 "slug":"1542837843-d4ba384f2a",
                 "firstname":"ABDESSELEM",
                 "lastname":"Karim",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"montpellier-herault-sp-c",
                    "name":"MONTPELLIER HERAULT SP.C.",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/montpellier-herault-sp-c\/logo"
                 }
              },
              {
                 "slug":"1550527164-f2a120983e",
                 "firstname":"Abdiaziz",
                 "lastname":"Hayle",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":null,
                    "name":null,
                    "logo_url":null
                 }
              },
              {
                 "slug":"abdifatah-mohamoud-sanay",
                 "firstname":"Abdifatah",
                 "lastname":"Mohamoud Sanay",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/arssen-rango-1537991497-bc5187cd7b\/profile-picture\/EE85523D-64A5-456C-B9F2-93EE01598B85.jpeg",
                 "club":{
                    "slug":"barva-if",
                    "name":"Barva IF",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/barva-if\/logo"
                 }
              },
              {
                 "slug":"abdifitaah-abuukar",
                 "firstname":"Abdifitah",
                 "lastname":"Hassan",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/abdifitaah-abuukar\/profile_picture\/c33ea7d7-d949-49a2-89c1-7a95355d8519",
                 "club":{
                    "slug":"sv-1919-rodenbach",
                    "name":"SV 1919 Rodenbach",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/sv-1919-rodenbach\/logo"
                 }
              },
              {
                 "slug":"abdirahman-waayeel-shifo",
                 "firstname":"Abdirahmän",
                 "lastname":"Waayeel Shifo",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/abdirahman-waayeel-shifo\/profile_picture\/fd8b5fbc6c0e4f1024ebb45528fb0f51",
                 "club":{
                    "slug":"ope-if",
                    "name":"Ope IF",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/ope-if\/logo"
                 }
              },
              {
                 "slug":"abdirizaq-shafii-1535823796",
                 "firstname":"Abdirizaq",
                 "lastname":"Shafii",
                 "profile_picture":"http:\/\/tonsser-production.imgix.net\/abdirizaq-shafii-1535823796\/profile_picture\/33c0b634f3b584d8a9e8d09bd2ac6ddc",
                 "club":{
                    "slug":"fc-aegeri",
                    "name":"FC Aegeri",
                    "logo_url":"http:\/\/tonsser-production.imgix.net\/clubs\/fc-aegeri\/logo"
                 }
              }
              ]
            }
            """
    }
}
