package com.adrianfaciu.teamcity.flowdockPlugin.settings

import com.adrianfaciu.teamcity.flowdockPlugin.util.logErrorMessage
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import com.intellij.openapi.util.JDOMUtil
import jetbrains.buildServer.configuration.ChangeListener
import jetbrains.buildServer.util.FileUtil
import jetbrains.buildServer.serverSide.ServerPaths
import jetbrains.buildServer.util.FileUtil.Processor
import org.jdom.Document
import org.jdom.Element
import java.io.File

class FlowdockMainConfig(val serverPaths: ServerPaths) : ChangeListener {
    var apiToken: String? = "" // "e9ce795411351be641ad845a4b910289"
    var apiEndpoint: String = "https://api.flowdock.com/messages"
    var isEnabled: Boolean? = true

    private val ENABLED = "enabled"
    private val TOKEN = "token"

    private val configDirectory = File(this.serverPaths.configDir, "flowdock")
    private val configFile = File(configDirectory, "flowdock-config.xml")

    override fun changeOccured(change: String?) {
        this.reloadConfiguration()
    }

    fun reloadConfiguration() {
        logInfoMessage("Configuration load: ${this.configFile.absolutePath}")

        this.checkAndCreateConfigFile()
        val rootNode = this.loadXmlFile(this.configFile)
        this.readFromNode(rootNode?.rootElement)
    }

    fun save(){
        logInfoMessage("Configuration save")

        FileUtil.processXmlFile(this.configFile, Processor {
            rootElement ->
                logInfoMessage("Processor parsing file: ${this.isEnabled.toString()} - ${this.apiToken}")
                rootElement.setAttribute(ENABLED, this.isEnabled.toString())
                rootElement.setAttribute(TOKEN, this.apiToken)
        })
    }

    fun saveFromNode(flowdockNode: Element?) {
        this.readFromNode(flowdockNode)
        this.save()
    }

    fun readFromNode(flowdockNode: Element?) {
        // Do not change existing if null ?!
        this.isEnabled = flowdockNode?.getAttribute(ENABLED)?.booleanValue ?: true
        this.apiToken = flowdockNode?.getAttribute(TOKEN)?.value ?: ""
    }

    private fun loadXmlFile(configFile: File): Document? {
        try {
            if (configFile.exists())
                return JDOMUtil.loadDocument(configFile)
        }
        catch (error: Exception) {
            logErrorMessage("Failed to load config file: ${error.message}")
        }
        return null
    }

    private fun checkAndCreateConfigFile() {
        if (this.configFile.exists())
            return

        configDirectory.mkdirs()

        this.configFile.writeText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
        this.configFile.appendText(System.lineSeparator())
        this.configFile.appendText("<flowdockconfig></flowdockconfig>")

        FileUtil.createIfDoesntExist(this.configFile)
    }
}