package com.adrianfaciu.teamcity.flowdockPlugin.util

import jetbrains.buildServer.log.Loggers

class LoggerManager() {
    val logger = Loggers.ACTIVITIES!!

    var infoMessageEnabled = true

    fun logInfoMessage(message: String) {
        if (!infoMessageEnabled)
            return

        logger.info("FlowdockNotifier: $message")
    }

    fun logErrorMessage(message: String) {
        logger.error("FlowdockNotifier: $message")
    }
}