package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.serverSide.settings.ProjectSettingsManager

class FlowdockSettingsRepo(val mainSettings: FlowdockMainConfig, val projectSettingsManager : ProjectSettingsManager) {
    fun getToken(projectId: String?): String? {
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