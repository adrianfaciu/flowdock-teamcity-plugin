package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockSettingsRepo
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.GsonBuilder

class FlowdockManager(val settings: FlowdockSettingsRepo) {
    fun sendNotification(notification: FlowdockNotification){
        if (!settings.getEnabledState()) {
            logInfoMessage("Notifier is disabled, don't send notifications")
            return
        }

        val builder = GsonBuilder()
        val gson = builder.create()
        var messageBody = gson.toJson(notification)

        logInfoMessage("Notification: $messageBody")

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        var response = Fuel.post(settings.getApiEndpoint())
                           .body(messageBody)
                           .response()

        logInfoMessage(response.toString())
    }
}