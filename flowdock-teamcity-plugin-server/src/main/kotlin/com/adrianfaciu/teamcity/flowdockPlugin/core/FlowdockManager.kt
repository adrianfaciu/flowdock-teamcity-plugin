package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.github.kittinunf.fuel.Fuel

class FlowdockManager {
    fun sendNotification(){

        var url = "testurl.com"
        var mesageToPost = """
{
"flow_token": "e9ce795411351be641ad845a4b910289",
  "event": "activity",
  "author": {
    "name": "Marty",
    "avatar": "https://avatars.githubusercontent.com/u/3017123?v=3"
  },
  "title": "updated ticket",
  "external_thread_id": "1234567",
  "thread": {
    "title": "Polish the flux capacitor",
    "fields": [{ "label": "Dustiness", "value": "5 - severe" }],
    "body": "The flux capacitor has been in storage for more than 30 years and it needs to be spick and span for the re-launch.",
    "external_url": "https://example.com/projects/bttf/tickets/1234567",
    "status": {
      "color": "green",
      "value": "open"
    }
  }
}
"""
        Fuel.post(url).body(mesageToPost).response()
    }
}