package com.adrianfaciu.teamcity.flowdockPlugin.ui

import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockMainConfig
import com.adrianfaciu.teamcity.flowdockPlugin.util.LoggerManager
import jetbrains.buildServer.controllers.admin.AdminPage
import jetbrains.buildServer.web.openapi.PagePlaces
import jetbrains.buildServer.web.openapi.PluginDescriptor
import javax.servlet.http.HttpServletRequest

/**
 * Custom UI for admin
 */
class FlowdockAdminTab(val mainConfig: FlowdockMainConfig, val logger: LoggerManager, pagePlaces: PagePlaces) : AdminPage(pagePlaces) {
    constructor(pagePlaces: PagePlaces, logger: LoggerManager, pluginDescriptor: PluginDescriptor, mainConfig: FlowdockMainConfig)
            : this(mainConfig, logger, pagePlaces) {

        this.pluginName = "Flowdock"
        this.tabTitle = "Flowdock Notifier"

        this.includeUrl = pluginDescriptor.getPluginResourcesPath("flowdockAdminTab.jsp")

        this.mainConfig.reloadConfiguration()

        register()
    }

    override fun fillModel(model: MutableMap<String, Any>, request: HttpServletRequest) {
        super.fillModel(model, request)

        model.put("token", this.mainConfig.apiToken ?: "")
        model.put("disabled", !(this.mainConfig.isEnabled ?: true))
        model.put("enableLogInfoMessages", this.mainConfig.enableLogInfoMessages ?: true)
    }

    override fun getGroup(): String {
        return jetbrains.buildServer.web.openapi.Groupable.SERVER_RELATED_GROUP
    }
}