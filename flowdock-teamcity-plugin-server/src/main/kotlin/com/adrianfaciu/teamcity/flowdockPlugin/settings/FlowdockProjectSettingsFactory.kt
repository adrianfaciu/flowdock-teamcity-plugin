package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.serverSide.settings.ProjectSettings
import jetbrains.buildServer.serverSide.settings.ProjectSettingsFactory
import jetbrains.buildServer.serverSide.settings.ProjectSettingsManager

/**
 * Factory for project level settings
 */
class FlowdockProjectSettingsFactory : ProjectSettingsFactory {
    constructor(projectSettingsManager : ProjectSettingsManager) {
        projectSettingsManager.registerSettingsFactory("flowdockNotifications", this)
    }

    override fun createProjectSettings(projectId: String?): ProjectSettings {
        return FlowdockProjectSettings(projectId)
    }
}