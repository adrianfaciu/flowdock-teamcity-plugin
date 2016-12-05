package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.*
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockSettingsRepo
import com.adrianfaciu.teamcity.flowdockPlugin.util.LoggerManager
import jetbrains.buildServer.serverSide.SProject
import jetbrains.buildServer.serverSide.SBuildServer
import jetbrains.buildServer.serverSide.SRunningBuild
import jetbrains.buildServer.users.SUser
import java.nio.charset.Charset
import java.security.MessageDigest

/**
 * Class used to create notifications from server events
 */
class NotificationBuilder(val flowdockConfig: FlowdockSettingsRepo, val tcServer: SBuildServer, val logger: LoggerManager) {
    val EVENT_TYPE = "activity"

    fun createNotification(type: NotificationType, build: SRunningBuild, users: MutableSet<SUser>, project: SProject): FlowdockNotification {
        return this.getBasicNotification(type, project, users, build)
    }

    fun <T> createNotification(type: NotificationType, collection: MutableCollection<T>, users: MutableSet<SUser>, project: SProject?): FlowdockNotification {
        return this.getBasicNotification(type, project, users)
    }

    fun createNotification(type: NotificationType, users: MutableSet<SUser>, project: SProject): FlowdockNotification {
        return this.getBasicNotification(type, project, users)
    }

    /**
     * Creates a basic notification object
     * Based on the event we can add different information to it
     */
    private fun getBasicNotification(type: NotificationType, project: SProject?, users: MutableSet<SUser>, build: SRunningBuild? = null): FlowdockNotification {
        logger.logInfoMessage("Creating basic notification")

        val notificationDetails = this.getNotificationDetails(type)

        val notification =  FlowdockNotification()
        notification.flow_token = this.flowdockConfig.getToken(project?.projectId)
        notification.event = EVENT_TYPE
        val user = users.first()
        notification.author = NotificationAuthor(user.name, this.getUserGravatar(user.email), user.email)

        notification.title = "<a href=\"${this.getTeamCityUrl(project?.projectId)}\"> TeamCity - ${project?.name}</a>"
        notification.body = this.getDefaultNotificationMessage(type, notificationDetails, build)
        notification.external_thread_id= "TC-${project?.projectId}"
        if (build != null) {
            notification.external_thread_id += "- ${build.fullName}"
        }

        notification.thread = this.getNotificationThread(notificationDetails, project, users, build)

        return notification
    }

    /**
     *  - Threads represent entities in external services
     *  - the thread object represents a new state for the existing thread
     *  - changing the thread_id / external_thread_id will change the target thread
     */
    private fun getNotificationThread(details: NotificationDetails?, project: SProject?, users: MutableSet<SUser>, build: SRunningBuild?): NotificationThread {
        logger.logInfoMessage("Creating notification thread")

        var thread = NotificationThread()
        thread.title = "Build status"
        if (build != null) {
            thread.title = build.fullName
        }

        thread.source = NotificationSource(100, "https://d3nmt5vlzunoa1.cloudfront.net/teamcity/files/2015/12/icon_TeamCity.png", "TeamCity")

        if (details != null) {
            thread.status = NotificationStatus(details.color, details.text)
        }

        return thread
    }

    /**
     * Read annotation from notification type enum
     */
    private fun getNotificationDetails(type: NotificationType): NotificationDetails? {
        try {
            logger.logInfoMessage("Reading notification details")
            return NotificationType::class.java.getField(type.toString()).annotations.first() as NotificationDetails
        }
        catch(error: Exception) {
            logger.logErrorMessage("Failed to get annotation")
            return null
        }
    }

    /**
     * Creating notification body (html allowed)
     * Should let user create a custom message...
     */
    private fun getDefaultNotificationMessage(type: NotificationType, details: NotificationDetails?, build: SRunningBuild?): String {
        var message = "${details?.text}"

        if (build != null) {
            message += " - Build No: " + build.buildNumber

            if (type == NotificationType.BuildFailed) {
                message += "<br>"
                message += build.compilationErrorMessages.first()
            }
        }

        return message
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