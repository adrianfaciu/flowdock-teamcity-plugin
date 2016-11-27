package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationAuthor
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationThread
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationType
import com.adrianfaciu.teamcity.flowdockPlugin.settings.FlowdockMainConfig
import jetbrains.buildServer.serverSide.SRunningBuild
import jetbrains.buildServer.users.SUser

class NotificationBuilder(val flowdockConfig: FlowdockMainConfig) {
    fun createNotification(type: NotificationType, build: SRunningBuild, users: MutableSet<SUser>): FlowdockNotification {
        var notification =  FlowdockNotification()
        notification.flow_token = this.flowdockConfig.apiToken
        notification.event = "activity"
        notification.author = NotificationAuthor("Adrian","https://avatars.githubusercontent.com/u/3017123?v=3", null)

        notification.title = "Build event"
        notification.body = "message from TC"
        notification.external_thread_id= "build"

        var thread = NotificationThread()
        thread.title = "Build status"
        notification.thread = thread

        return notification
    }
}