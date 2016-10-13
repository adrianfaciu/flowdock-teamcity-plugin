package com.adrianfaciu.teamcity.flowdockPlugin.ui

import jetbrains.buildServer.controllers.admin.AdminPage
import jetbrains.buildServer.web.openapi.Groupable
import jetbrains.buildServer.web.openapi.PagePlaces
import jetbrains.buildServer.web.openapi.PluginDescriptor

// This we need for general top level settings
// Project level things can copy this

class FlowdockAdminTab : AdminPage {
    constructor(pagePlaces: PagePlaces, pluginDescriptor: PluginDescriptor) : super(pagePlaces) {
        pluginName = "Flowdock"
        tabTitle = "Flowdock Notifier"
        register()
    }

    override fun getGroup(): String {
        return jetbrains.buildServer.web.openapi.Groupable.SERVER_RELATED_GROUP
    }
}