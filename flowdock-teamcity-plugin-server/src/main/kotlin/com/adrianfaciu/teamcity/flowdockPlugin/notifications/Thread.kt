package com.adrianfaciu.teamcity.flowdockPlugin.notifications

class Thread {
    var body: String? = null

    var title: String? = null

    var status: Status? = null

    var external_url: String? = null

    var fields: Array<Fields>? = null

    override fun toString(): String {
        return "ClassPojo [body = $body, title = $title, status = $status, external_url = $external_url, fields = $fields]"
    }
}
