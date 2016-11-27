package com.adrianfaciu.teamcity.flowdockPlugin.ui

import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import jetbrains.buildServer.controllers.BaseController
import jetbrains.buildServer.serverSide.SBuildServer
import jetbrains.buildServer.web.openapi.PluginDescriptor
import jetbrains.buildServer.web.openapi.WebControllerManager
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// Good sample here: https://github.com/wix/wix-maven-teamcity-plugin/blob/master/server/src/main/java/com/wixpress/ci/teamcity/dependenciesTab/DependenciesTabAjaxController.java

class FlowdockAdminController : BaseController {
    var webManager: WebControllerManager
    var pluginPath: String?

    constructor(server: SBuildServer, webManager: WebControllerManager, pluginDescriptor: PluginDescriptor) : super(server) {
        this.webManager = webManager
        this.pluginPath = pluginDescriptor.pluginResourcesPath
    }

    fun register() {
        this.webManager.registerController("/flowdockNotifier/flowdockAdminTab.html", this)
        logInfoMessage("Registering admin controller")
    }

    override fun doHandle(request: HttpServletRequest, response: HttpServletResponse): ModelAndView? {
        logInfoMessage("Handling request")

        val token = request.getParameter("token")
        logInfoMessage("Save: $token")

        return null
    }
}