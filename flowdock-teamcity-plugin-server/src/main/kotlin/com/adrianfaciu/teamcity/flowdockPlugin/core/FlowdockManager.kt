package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.util.SettingsManager
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.GsonBuilder

class FlowdockManager {
    private val settingsManager = SettingsManager()

    fun sendNotification(notification: FlowdockNotification){
        val builder = GsonBuilder()
        val gson = builder.create()
        var messageBody = gson.toJson(notification)

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        var response = Fuel.post(settingsManager.flowdockApiEndpoint)
                           .body(messageBody)
                           .response()

        logInfoMessage(response.toString())
    }
}