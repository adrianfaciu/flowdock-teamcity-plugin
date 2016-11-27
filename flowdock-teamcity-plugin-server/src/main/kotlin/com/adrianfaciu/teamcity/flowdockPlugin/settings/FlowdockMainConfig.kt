package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.configuration.ChangeListener
import org.jdom.Element

class FlowdockMainConfig(var apiToken: String, var apiEndpoint: String) : ChangeListener {
    override fun changeOccured(change: String?) {
        this.loadConfiguration()
    }

    private fun loadConfiguration() {
        this.apiToken = "e9ce795411351be641ad845a4b910289"
        this.apiEndpoint = "https://api.flowdock.com/messages"
    }

    fun saveFromNode(flowdockNode: Element?) {
        if (flowdockNode == null) {
            return
        }

        // Save here
    }

    fun readFromNode(flowdockNode: Element?) {
        // Load here
    }
}