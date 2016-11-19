package com.adrianfaciu.teamcity.flowdockPlugin.notifications

class NotificationThread {
    var body: String? = null

    var title: String? = null

    var status: NotificationStatus? = null

    var external_url: String? = null

    var fields: Array<NotificationFields>? = null

    override fun toString(): String {
        return "[body = $body, title = $title, status = $status, external_url = $external_url, fields = $fields]"
    }
}
