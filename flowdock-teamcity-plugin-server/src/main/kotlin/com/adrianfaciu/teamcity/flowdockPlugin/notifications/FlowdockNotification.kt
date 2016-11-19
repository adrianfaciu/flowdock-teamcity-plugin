package com.adrianfaciu.teamcity.flowdockPlugin.notifications

class FlowdockNotification {
    var author: NotificationAuthor? = null

    var external_thread_id: String? = null

    var title: String? = null

    var event: String? = null

    var thread: NotificationThread? = null

    var thread_id: String? = null

    var body: String? = null

    var flow_token: String? = null

    override fun toString(): String {
        return "[author = $author, body = $body, external_thread_id = $external_thread_id, title = $title, event = $event, thread = $thread, thread_id = $thread_id, flow_token = $flow_token]"
    }
}