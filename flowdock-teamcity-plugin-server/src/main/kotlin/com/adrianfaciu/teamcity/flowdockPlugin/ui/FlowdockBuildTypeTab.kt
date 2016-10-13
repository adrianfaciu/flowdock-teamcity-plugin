package com.adrianfaciu.teamcity.flowdockPlugin.ui

import jetbrains.buildServer.serverSide.ProjectManager
import jetbrains.buildServer.serverSide.SBuildType
import jetbrains.buildServer.users.SUser
import jetbrains.buildServer.web.openapi.WebControllerManager
import jetbrains.buildServer.web.openapi.buildType.BuildTypeTab
import javax.servlet.http.HttpServletRequest

class FlowdockBuildTypeTab : BuildTypeTab {
    constructor(manager: WebControllerManager, projectManager: ProjectManager)
        : super("Flowdock", "Flowdock notifier", manager, projectManager) {

    }

    override fun fillModel(p0: MutableMap<String, Any>, p1: HttpServletRequest, p2: SBuildType, p3: SUser?) {

    }
}

