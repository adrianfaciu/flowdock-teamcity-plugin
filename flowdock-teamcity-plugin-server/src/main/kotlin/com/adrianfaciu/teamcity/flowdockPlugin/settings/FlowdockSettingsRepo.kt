package com.adrianfaciu.teamcity.flowdockPlugin.settings

import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import jetbrains.buildServer.serverSide.settings.ProjectSettingsManager

/**
 * Settings are saved at multiple levels: admin, project, build
 * This helps retrieving the one that we need, or the one that exists
 * Should act as proxy for reading and using the settings
 */
class FlowdockSettingsRepo(val mainSettings: FlowdockMainConfig, val projectSettingsManager : ProjectSettingsManager) {
    fun getToken(projectId: String? = null): String? {
        var token = mainSettings.apiToken
        if (!projectId.isNullOrBlank()) {
            val settings = this.projectSettingsManager.getSettings(projectId, "flowdockNotifications") as FlowdockProjectSettings
            if (!settings.projectToken.isNullOrBlank()) {
                token = settings.projectToken;
            }
        }

        logInfoMessage("Token: $token")
        return token
    }

    fun getEnabledState(): Boolean {
        val state = this.mainSettings.isEnabled ?: true
        logInfoMessage("State: $state")

        return state
    }

    fun getApiEndpoint(): String {
        return "https://api.flowdock.com/messages"
    }
}