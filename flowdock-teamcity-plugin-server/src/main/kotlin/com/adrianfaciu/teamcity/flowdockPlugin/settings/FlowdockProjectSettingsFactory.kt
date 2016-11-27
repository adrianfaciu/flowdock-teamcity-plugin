package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.serverSide.settings.ProjectSettings
import jetbrains.buildServer.serverSide.settings.ProjectSettingsFactory
import jetbrains.buildServer.serverSide.settings.ProjectSettingsManager

class FlowdockProjectSettingsFactory : ProjectSettingsFactory {
    constructor(projectSettingsManager : ProjectSettingsManager) {
        projectSettingsManager.registerSettingsFactory("flowdockNotifications", this)
    }

    override fun createProjectSettings(projectId: String?): ProjectSettings {
        return FlowdockProjectSettings()
    }
}