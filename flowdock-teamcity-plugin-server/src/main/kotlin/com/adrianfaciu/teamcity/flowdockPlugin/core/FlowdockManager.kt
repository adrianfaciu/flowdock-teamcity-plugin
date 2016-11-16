package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.Author
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.github.kittinunf.fuel.Fuel
import com.google.gson.GsonBuilder

class FlowdockManager {
    fun sendNotification(){

        var url = "testurl.com"

        var notification =  FlowdockNotification()
        // set all objects and add constructor

        val builder = GsonBuilder()
        val gson = builder.create()
        var messageBody = gson.toJson(notification)

        Fuel.post(url).body(messageBody).response()
    }
}

/*

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

 */