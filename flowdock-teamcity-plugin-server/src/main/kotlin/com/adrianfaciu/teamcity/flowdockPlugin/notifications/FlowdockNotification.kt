package com.adrianfaciu.teamcity.flowdockPlugin.notifications

class FlowdockNotification {
    var author: Author? = null

    var external_thread_id: String? = null

    var title: String? = null

    var event: String? = null

    var thread: Thread? = null

    var flow_token: String? = null

    override fun toString(): String {
        return "ClassPojo [author = $author, external_thread_id = $external_thread_id, title = $title, event = $event, thread = $thread, flow_token = $flow_token]"
    }
}