package com.adrianfaciu.teamcity.flowdockPlugin.util

import jetbrains.buildServer.log.Loggers

val logger = Loggers.ACTIVITIES!!

fun logInfoMessage(message: String) {
    logger.info("FlowdockNotifier: $message")
}

fun logErrorMessage(message: String) {
    logger.error("FlowdockNotifier: $message")
}
