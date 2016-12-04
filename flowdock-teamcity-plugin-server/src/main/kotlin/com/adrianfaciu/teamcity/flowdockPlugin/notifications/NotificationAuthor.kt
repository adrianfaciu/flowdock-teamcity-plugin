package com.adrianfaciu.teamcity.flowdockPlugin.notifications

/**
 * Who created the notification
 * Email will be used to fetch image from Gravatar
 */
data class NotificationAuthor(val name: String?, val avatar: String?, val email: String?)