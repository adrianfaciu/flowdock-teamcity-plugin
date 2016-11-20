package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.FlowdockNotification
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationAuthor
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationThread
import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationType
import jetbrains.buildServer.serverSide.SRunningBuild
import jetbrains.buildServer.users.SUser

class NotificationBuilder {
    fun createNotification(type: NotificationType, build: SRunningBuild, users: MutableSet<SUser>): FlowdockNotification {
        var notification =  FlowdockNotification()
        notification.flow_token = "e9ce795411351be641ad845a4b910289"
        notification.event = "activity"
        notification.author = NotificationAuthor("Adrian","https://avatars.githubusercontent.com/u/3017123?v=3", null)
        notification.title = "Build event"
        notification.body = "message from TC"
        notification.external_thread_id= "build"

        var thread = NotificationThread()
        thread.title = "Build status"
        notification.thread = thread

        return notification;
    }
}