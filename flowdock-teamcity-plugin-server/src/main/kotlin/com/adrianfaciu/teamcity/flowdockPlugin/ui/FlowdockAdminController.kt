package com.adrianfaciu.teamcity.flowdockPlugin.ui

import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockMainConfig
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import jetbrains.buildServer.controllers.BaseController
import jetbrains.buildServer.serverSide.SBuildServer
import jetbrains.buildServer.web.openapi.PluginDescriptor
import jetbrains.buildServer.web.openapi.WebControllerManager
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// Good sample here: https://github.com/wix/wix-maven-teamcity-plugin/blob/master/server/src/main/java/com/wixpress/ci/teamcity/dependenciesTab/DependenciesTabAjaxController.java

class FlowdockAdminController(val server: SBuildServer,
                              val webManager: WebControllerManager,
                              val pluginDescriptor: PluginDescriptor,
                              val mainConfig: FlowdockMainConfig) : BaseController() {

    fun register() {
        this.webManager.registerController("/flowdockNotifier/flowdockAdminTab.html", this)
        logInfoMessage("Registering admin controller")
    }

    override fun doHandle(request: HttpServletRequest, response: HttpServletResponse): ModelAndView? {
        logInfoMessage("Handling request")

        val token = request.getParameter("token")
        logInfoMessage("Save: $token")

        this.mainConfig.apiToken = token
        this.mainConfig.save()

        return null
    }
}