package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationType
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.GsonBuilder

class FlowdockManager {
    fun sendNotification(notification: FlowdockNotification){
        var url = "https://api.flowdock.com/messages"

        val builder = GsonBuilder()
        val gson = builder.create()
        var messageBody = gson.toJson(notification)

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        var response = Fuel.post(url).body(messageBody).response()

        logInfoMessage(response.toString())
    }

    fun shouldSend(type: NotificationType): Boolean {
        // TODO check if we want/need to send this notification
        return true;
    }
}