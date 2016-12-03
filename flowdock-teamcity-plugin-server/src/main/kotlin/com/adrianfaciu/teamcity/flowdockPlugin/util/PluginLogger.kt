package com.adrianfaciu.teamcity.flowdockPlugin.util

import jetbrains.buildServer.log.Loggers

val logger = Loggers.ACTIVITIES!!
val logInfoMessages = true //TODO Handle from config

fun logInfoMessage(message: String) {
    if (!logInfoMessages)
        return

    logger.info("FlowdockNotifier: $message")
}

fun logErrorMessage(message: String) {
    logger.error("FlowdockNotifier: $message")
}

//TODO Convert to class ?
