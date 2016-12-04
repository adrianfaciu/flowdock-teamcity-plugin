package com.adrianfaciu.teamcity.flowdockPlugin.util

import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockSettingsRepo
import jetbrains.buildServer.log.Loggers

class LoggerManager(val settings: FlowdockSettingsRepo) {
    val logger = Loggers.ACTIVITIES!!

    fun logInfoMessage(message: String) {
        if (!settings.isLogInfoEnabled())
            return

        logger.info("FlowdockNotifier: $message")
    }

    fun logErrorMessage(message: String) {
        logger.error("FlowdockNotifier: $message")
    }
}