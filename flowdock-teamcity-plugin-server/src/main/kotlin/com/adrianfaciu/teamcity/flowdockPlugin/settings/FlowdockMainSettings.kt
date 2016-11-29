package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.serverSide.MainConfigProcessor
import jetbrains.buildServer.serverSide.SBuildServer
import jetbrains.buildServer.serverSide.ServerPaths
import org.jdom.Element

class FlowdockMainSettings(val server: SBuildServer, val serverPaths : ServerPaths, val mainConfig: FlowdockMainConfig) : MainConfigProcessor {
    val flowdockNodeName = "flowdockNotifications"

    override fun writeTo(rootElement: Element?) {
        var flowdockNode = rootElement?.getChild(this.flowdockNodeName)
        this.mainConfig.saveFromNode(flowdockNode)
    }

    override fun readFrom(rootElement: Element?) {
        this.mainConfig.readFromNode(rootElement)
        this.mainConfig.save()
    }

    fun register() {
        this.server.registerExtension(MainConfigProcessor::class.java, this.flowdockNodeName, this)
    }
}