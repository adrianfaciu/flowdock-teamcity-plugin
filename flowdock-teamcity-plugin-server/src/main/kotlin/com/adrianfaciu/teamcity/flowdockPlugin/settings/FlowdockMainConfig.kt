package com.adrianfaciu.teamcity.flowdockPlugin.settings

import com.adrianfaciu.teamcity.flowdockPlugin.util.LoggerManager
import com.intellij.openapi.util.JDOMUtil
import jetbrains.buildServer.configuration.ChangeListener
import jetbrains.buildServer.util.FileUtil
import jetbrains.buildServer.serverSide.ServerPaths
import jetbrains.buildServer.util.FileUtil.Processor
import org.jdom.Document
import org.jdom.Element
import java.io.File

class FlowdockMainConfig(val serverPaths: ServerPaths, val logger: LoggerManager) : ChangeListener {
    var apiToken: String? = ""
    var isEnabled: Boolean? = true
    var enableLogInfoMessages: Boolean? = false;

    private val ENABLED = "enabled"
    private val TOKEN = "token"
    private val LOG_INFO = "enableLogInfoMessages"

    private val configDirectory = File(this.serverPaths.configDir, "flowdock")
    private val configFile = File(configDirectory, "flowdock-config.xml")

    override fun changeOccured(change: String?) {
        this.reloadConfiguration()
    }

    fun reloadConfiguration() {
        logger.logInfoMessage("Configuration load: ${this.configFile.absolutePath}")

        this.checkAndCreateConfigFile()
        val rootNode = this.loadXmlFile(this.configFile)
        this.readFromNode(rootNode?.rootElement)
    }

    fun save(){
        logger.logInfoMessage("Configuration save")

        FileUtil.processXmlFile(this.configFile, Processor {
            rootElement ->
                logger.logInfoMessage("Processor parsing file: ${this.isEnabled.toString()} - ${this.apiToken}")
                rootElement.setAttribute(ENABLED, this.isEnabled.toString())
                rootElement.setAttribute(TOKEN, this.apiToken)
                rootElement.setAttribute(LOG_INFO, this.enableLogInfoMessages.toString())
        })
    }

    fun saveFromNode(flowdockNode: Element?) {
        this.readFromNode(flowdockNode)
        this.save()
    }

    fun readFromNode(flowdockNode: Element?) {
        this.isEnabled = flowdockNode?.getAttribute(ENABLED)?.booleanValue ?: true
        this.apiToken = flowdockNode?.getAttribute(TOKEN)?.value ?: ""
        this.enableLogInfoMessages = flowdockNode?.getAttribute(LOG_INFO)?.booleanValue ?: false
    }

    private fun loadXmlFile(configFile: File): Document? {
        try {
            if (configFile.exists())
                return JDOMUtil.loadDocument(configFile)
        }
        catch (error: Exception) {
            logger.logErrorMessage("Failed to load config file: ${error.message}")
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