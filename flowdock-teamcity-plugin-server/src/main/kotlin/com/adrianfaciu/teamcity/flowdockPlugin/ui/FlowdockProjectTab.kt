package com.adrianfaciu.teamcity.flowdockPlugin.ui

import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockProjectSettings
import jetbrains.buildServer.serverSide.ProjectManager
import jetbrains.buildServer.serverSide.SProject
import jetbrains.buildServer.serverSide.settings.ProjectSettingsManager
import jetbrains.buildServer.users.SUser
import jetbrains.buildServer.web.openapi.PagePlaces
import jetbrains.buildServer.web.openapi.PluginDescriptor
import jetbrains.buildServer.web.openapi.project.ProjectTab
import javax.servlet.http.HttpServletRequest

class FlowdockProjectTab(val projectSettingsManager : ProjectSettingsManager, pagePlaces: PagePlaces, projectManager: ProjectManager, descriptor: PluginDescriptor)
    : ProjectTab("Flowdock notification", "Flowdock notification", pagePlaces, projectManager) {

    override fun fillModel(model: MutableMap<String, Any>, request: HttpServletRequest, project: SProject, user: SUser?) {
        val settings = this.projectSettingsManager.getSettings(project.projectId, "flowdockNotifications") as FlowdockProjectSettings

        super.fillModel(model, request)

        model.put("token", settings.projectToken ?: "")
        model.put("projectId", project.projectId)
    }

    init {
        this.includeUrl = descriptor.getPluginResourcesPath("flowdockProjectTab.jsp")
    }
}