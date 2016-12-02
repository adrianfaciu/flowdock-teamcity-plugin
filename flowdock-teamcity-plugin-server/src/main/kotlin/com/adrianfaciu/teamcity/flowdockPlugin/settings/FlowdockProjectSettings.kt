package com.adrianfaciu.teamcity.flowdockPlugin.settings

import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import jetbrains.buildServer.serverSide.settings.ProjectSettings
import org.jdom.Element

class FlowdockProjectSettings() : ProjectSettings {
    private val PROJECT_TOKEN = "projectToken"

    var projectToken: String? = ""

    override fun writeTo(rootElement: Element?) {
        logInfoMessage("Writing project settings to file")
        rootElement?.setAttribute(PROJECT_TOKEN, this.projectToken)
    }

    override fun readFrom(rootElement: Element?) {
        logInfoMessage("Reading project settings from file")
        this.projectToken = rootElement?.getAttribute(PROJECT_TOKEN).toString()
    }

    override fun dispose() {
        logInfoMessage("Disposing FlowdockProjectSettings")
    }
}