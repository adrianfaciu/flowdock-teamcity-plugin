package com.adrianfaciu.teamcity.flowdockPlugin.notifications

/**
 * The application that posted this thread. Includes the application name and icon.
 */
data class NotificationSource(val id: Int, val icon: String, val name: String)