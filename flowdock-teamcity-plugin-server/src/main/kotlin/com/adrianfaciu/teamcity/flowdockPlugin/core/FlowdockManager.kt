package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockSettingsRepo
import com.adrianfaciu.teamcity.flowdockPlugin.util.LoggerManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.GsonBuilder

/**
 * Sending notifications to FlowDock using the REST API
 */
class FlowdockManager(val settings: FlowdockSettingsRepo, val logger: LoggerManager) {
    fun sendNotification(notification: FlowdockNotification){
        if (!settings.getEnabledState()) {
            logger.logInfoMessage("Notifier is disabled, don't send notifications")
            return
        }

        val builder = GsonBuilder()
        val serializer = builder.create()
        var messageBody = serializer.toJson(notification)

        logger.logInfoMessage("Notification: $messageBody")

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        var response = Fuel.post(settings.getApiEndpoint())
                           .body(messageBody)
                           .response()

        logger.logInfoMessage(response.toString())
    }
}