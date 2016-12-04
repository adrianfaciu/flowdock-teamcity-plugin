package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.serverSide.settings.ProjectSettings
import org.jdom.Element

/**
 * Settings handler for project level
 */
class FlowdockProjectSettings(val projectId: String?) : ProjectSettings {
    private val PROJECT_TOKEN = "projectToken"

    var projectToken: String? = ""

    override fun writeTo(rootElement: Element?) {
        rootElement?.setAttribute(PROJECT_TOKEN, this.projectToken)
    }

    override fun readFrom(rootElement: Element?) {
        this.projectToken = rootElement?.getAttribute(PROJECT_TOKEN)?.value
    }

    override fun dispose() {
    }
}