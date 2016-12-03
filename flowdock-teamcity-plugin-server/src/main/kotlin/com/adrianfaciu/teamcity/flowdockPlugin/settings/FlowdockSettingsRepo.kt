package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.serverSide.settings.ProjectSettingsManager

/**
 * Settings are saved at multiple levels: admin, project, build
 * This helps retrieving the one that we need, or the one that exists
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

        return token
    }
}