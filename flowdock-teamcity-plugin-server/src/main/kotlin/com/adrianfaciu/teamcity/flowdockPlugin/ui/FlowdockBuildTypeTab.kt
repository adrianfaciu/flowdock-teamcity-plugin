package com.adrianfaciu.teamcity.flowdockPlugin.ui

import jetbrains.buildServer.serverSide.ProjectManager
import jetbrains.buildServer.serverSide.SBuildType
import jetbrains.buildServer.users.SUser
import jetbrains.buildServer.web.openapi.PluginDescriptor
import jetbrains.buildServer.web.openapi.WebControllerManager
import jetbrains.buildServer.web.openapi.buildType.BuildTypeTab
import javax.servlet.http.HttpServletRequest

class FlowdockBuildTypeTab(manager: WebControllerManager, projectManager: ProjectManager, pluginDescriptor: PluginDescriptor)
    : BuildTypeTab("Flowdock", "Flowdock notifier", manager, projectManager) {

    override fun fillModel(p0: MutableMap<String, Any>, p1: HttpServletRequest, p2: SBuildType, p3: SUser?) {
    }

    init {
        this.includeUrl = pluginDescriptor.getPluginResourcesPath("flowdockBuildTypeTab.jsp")
    }
}

