package com.adrianfaciu.teamcity.flowdockPlugin.util

class SettingsManager {
    public var flowdockApiEndpoint: String = "https://api.flowdock.com/messages"

    fun getFlowToken(): String {
        return "e9ce795411351be641ad845a4b910289"
    }

    fun getCustomMessage(): String {
        return "read some custom formatting and values the user can set"
    }
}
