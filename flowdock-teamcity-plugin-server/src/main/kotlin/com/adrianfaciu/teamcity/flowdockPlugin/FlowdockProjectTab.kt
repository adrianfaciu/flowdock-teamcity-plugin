package com.adrianfaciu.teamcity.flowdockPlugin

import jetbrains.buildServer.serverSide.ProjectManager
import jetbrains.buildServer.serverSide.SProject
import jetbrains.buildServer.users.SUser
import jetbrains.buildServer.web.openapi.PagePlaces
import jetbrains.buildServer.web.openapi.PluginDescriptor
import jetbrains.buildServer.web.openapi.project.ProjectTab
import javax.servlet.http.HttpServletRequest

class FlowdockProjectTab : ProjectTab {
    constructor(pagePlaces: PagePlaces, projectManager: ProjectManager, descriptor: PluginDescriptor)
        : super("Flowdock notification", "Flowdock notification", pagePlaces, projectManager, descriptor.getPluginResourcesPath("FlowdockNotification/flowdockProjectTab.jsp")) {
        // TODO
    }

    override fun fillModel(map: MutableMap<String, Any>, request: HttpServletRequest, project: SProject, user: SUser?) {
        // TODO
    }
}