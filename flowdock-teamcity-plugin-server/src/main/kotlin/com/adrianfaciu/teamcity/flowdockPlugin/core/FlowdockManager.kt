package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationAuthor
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationThread
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.GsonBuilder
import com.intellij.openapi.diagnostic.Logger
import jetbrains.buildServer.log.Loggers

class FlowdockManager {
    private var LOG: Logger = Loggers.ACTIVITIES

    fun sendNotification(){
        var url = "https://api.flowdock.com/messages"

        var notification =  FlowdockNotification()
        notification.flow_token = "e9ce795411351be641ad845a4b910289"
        notification.event = "activity"
        notification.author = NotificationAuthor("Adrian","https://avatars.githubusercontent.com/u/3017123?v=3", null)
        notification.title = "Build event"
        notification.body = "message from TC"
        notification.external_thread_id= "build"

        var thread = NotificationThread()
        thread.title = "Build status"
        notification.thread = thread

        val builder = GsonBuilder()
        val gson = builder.create()
        var messageBody = gson.toJson(notification)

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        var response = Fuel.post(url).body(messageBody).response()
        this.LOG.info(response.toString())
    }
}