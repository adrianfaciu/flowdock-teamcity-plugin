package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockMainConfig
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.GsonBuilder

class FlowdockManager(val flowdockConfig: FlowdockMainConfig) {
    fun sendNotification(notification: FlowdockNotification){
        val builder = GsonBuilder()
        val gson = builder.create()
        var messageBody = gson.toJson(notification)

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        var response = Fuel.post(this.flowdockConfig.apiEndpoint)
                           .body(messageBody)
                           .response()

        logInfoMessage(response.toString())
    }
}