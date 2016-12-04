package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.*
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockSettingsRepo
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import jetbrains.buildServer.serverSide.SProject
import jetbrains.buildServer.serverSide.SBuildServer
import jetbrains.buildServer.serverSide.SRunningBuild
import jetbrains.buildServer.users.SUser
import java.nio.charset.Charset
import java.security.MessageDigest

class NotificationBuilder(val flowdockConfig: FlowdockSettingsRepo, val tcServer: SBuildServer) {
    val EVENT_TYPE = "activity"

    fun createNotification(type: NotificationType, build: SRunningBuild, users: MutableSet<SUser>, project: SProject): FlowdockNotification {
        return this.getBasicNotification(type, project, users)
    }

    fun <T> createNotification(type: NotificationType, collection: MutableCollection<T>, users: MutableSet<SUser>, project: SProject?): FlowdockNotification {
        return this.getBasicNotification(type, project, users)
    }

    fun createNotification(type: NotificationType, users: MutableSet<SUser>, project: SProject): FlowdockNotification {
        return this.getBasicNotification(type, project, users)
    }

    private fun getBasicNotification(type: NotificationType, project: SProject?, users: MutableSet<SUser>): FlowdockNotification {
        logInfoMessage("Creating basic notification")

        val notification =  FlowdockNotification()
        notification.flow_token = this.flowdockConfig.getToken(project?.projectId)
        notification.event = EVENT_TYPE
        val user = users.first()
        notification.author = NotificationAuthor(user.name, this.getUserGravatar(user.email), user.email)

        notification.title = "<a href=\"${this.getTeamCityUrl(project?.projectId)}\"> TeamCity - ${project?.name} event</a>"
        notification.body = this.getDefaultNotificationMessage(type)
        notification.external_thread_id= "TC-${project?.name}"

        notification.thread = this.getNotificationThread(type, project, users)

        return notification
    }

    /**
     *  - Threads represent entities in external services
     *  - the thread object represents a new state for the existing thread
     *  - changing the thread_id / external_thread_id will change the target thread
     */
    private fun getNotificationThread(type: NotificationType, project: SProject?, users: MutableSet<SUser>): NotificationThread {
        logInfoMessage("Creating notification thread")

        var thread = NotificationThread()
        thread.title = type.toString()

        thread.source = NotificationSource(100, "https://d3nmt5vlzunoa1.cloudfront.net/teamcity/files/2015/12/icon_TeamCity.png", "TeamCity")

        val status = this.getNotificationDetails(type)
        if (status != null) {
            thread.status = NotificationStatus(status.color, status.text)
        }

        // Title, body, external_url.. same as notification
        // ACTIONS & FIELDS

        return thread
    }

    /**
     * Read annotation from notification type enum
     */
    private fun getNotificationDetails(type: NotificationType): NotificationDetails? {
        try {
            logInfoMessage("Reading notification details")
            return NotificationType::class.java.getField(type.toString()).annotations.first() as NotificationDetails
        }
        catch(error: Exception) {
            logInfoMessage("Failed to get annotation")
            return null
        }
    }

    /**
     * html allowed - what to put here ? - custom message from TC ?
     */
    private fun getDefaultNotificationMessage(type: NotificationType): String {
        return "Build status: $type"
    }

    /**
     * Return the main server url if generic event or project overview page if specific
     */
    private fun getTeamCityUrl(projectId: String?): String {
        var address = tcServer.rootUrl
        if (!projectId.isNullOrEmpty()) {
            address += "/project.html?projectId=$projectId&tab=projectOverview"
        }

        return address
    }

    /**
     * Use gravatar for user images
     */
    private fun getUserGravatar(email: String?): String {
        val emailBytes = email?.trim()?.toLowerCase()?.toByteArray()
        var hash = MessageDigest.getInstance("MD5").digest(emailBytes)
        val userHash = hash.toString(Charset.forName("UTF-8"))
        val userGravatar = "https://www.gravatar.com/avatar/$userHash"

        return userGravatar
    }
}