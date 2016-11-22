package com.adrianfaciu.teamcity.flowdockPlugin.ui

import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import jetbrains.buildServer.controllers.admin.AdminPage
import jetbrains.buildServer.web.openapi.PagePlaces
import jetbrains.buildServer.web.openapi.PluginDescriptor
import javax.servlet.http.HttpServletRequest

class FlowdockAdminTab : AdminPage {
    constructor(pagePlaces: PagePlaces, pluginDescriptor: PluginDescriptor) : super(pagePlaces) {
        this.pluginName = "Flowdock"
        this.tabTitle = "Flowdock Notifier"

        this.includeUrl = pluginDescriptor.getPluginResourcesPath("flowdockAdminTab.jsp")
        logInfoMessage("Include UR: ${this.includeUrl}")

        register()
        logInfoMessage("Registered admin tab")
    }

    override fun fillModel(model: MutableMap<String, Any>, request: HttpServletRequest) {
        super.fillModel(model, request)
        model.put("token", "mycooltoken")
        model.put("disabled", false)
    }

    override fun getGroup(): String {
        return jetbrains.buildServer.web.openapi.Groupable.SERVER_RELATED_GROUP
    }
}