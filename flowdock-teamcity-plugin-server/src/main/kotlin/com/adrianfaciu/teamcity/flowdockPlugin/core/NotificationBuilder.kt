package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationAuthor
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationThread
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationType
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockMainConfig
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockSettingsRepo
import jetbrains.buildServer.serverSide.SProject
import jetbrains.buildServer.serverSide.SRunningBuild
import jetbrains.buildServer.users.SUser

class NotificationBuilder(val flowdockConfig: FlowdockSettingsRepo) {
    fun createNotification(type: NotificationType, build: SRunningBuild, users: MutableSet<SUser>): FlowdockNotification {
        return this.getBasicNotification(type, build.projectId)
    }

    fun <T> createNotification(type: NotificationType, collection: MutableCollection<T>, users: MutableSet<SUser>, project: SProject?): FlowdockNotification {
        return this.getBasicNotification(type, project?.projectId)
    }

    fun createNotification(type: NotificationType, users: MutableSet<SUser>, project: SProject): FlowdockNotification {
        return this.getBasicNotification(type, project.projectId)
    }

    private fun getBasicNotification(type: NotificationType, projectId: String?): FlowdockNotification {
        var notification =  FlowdockNotification()
        notification.flow_token = this.flowdockConfig.getToken(projectId)
        notification.event = "activity"
        notification.author = NotificationAuthor("Adrian","https://avatars.githubusercontent.com/u/3017123?v=3", null)

        notification.title = "Build event"
        notification.body = "message from TC"
        notification.external_thread_id= "build"

        var thread = NotificationThread()
        thread.title = type.toString()
        notification.thread = thread

        return notification
    }
}