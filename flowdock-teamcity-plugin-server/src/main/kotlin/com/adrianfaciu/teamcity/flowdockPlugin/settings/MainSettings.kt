package com.adrianfaciu.teamcity.flowdockPlugin.settings

import jetbrains.buildServer.serverSide.MainConfigProcessor
import org.jdom.Element

//TODO Could use the json lib to serialize/deserialize object

class MainSettings : MainConfigProcessor {
    override fun writeTo(p0: Element?) {
        // write to file
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun readFrom(p0: Element?) {
        // read from file
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}