package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.intellij.openapi.diagnostic.Logger
import jetbrains.buildServer.Build
import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.notification.Notificator
import jetbrains.buildServer.notification.NotificatorRegistry
import jetbrains.buildServer.responsibility.ResponsibilityEntry
import jetbrains.buildServer.responsibility.TestNameResponsibilityEntry
import jetbrains.buildServer.serverSide.*
import jetbrains.buildServer.serverSide.mute.MuteInfo
import jetbrains.buildServer.serverSide.problems.BuildProblemInfo
import jetbrains.buildServer.tests.TestName
import jetbrains.buildServer.users.SUser
import jetbrains.buildServer.vcs.VcsRoot

class FlowdockNotificator : Notificator {
    private var LOG: Logger = Loggers.ACTIVITIES

    constructor(notificatorRegistry: NotificatorRegistry) {
        notificatorRegistry.register(this)
    }

    fun register(){

    }

    override fun notifyBuildProblemsUnmuted(p0: MutableCollection<BuildProblemInfo>, p1: MuteInfo, p2: SUser?, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyLabelingFailed(p0: Build, p1: VcsRoot, p2: Throwable, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyResponsibleChanged(p0: SBuildType, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyResponsibleChanged(p0: TestNameResponsibilityEntry?, p1: TestNameResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyResponsibleChanged(p0: MutableCollection<TestName>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildSuccessful(p0: SRunningBuild, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildFailed(p0: SRunningBuild, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildProblemResponsibleChanged(p0: MutableCollection<BuildProblemInfo>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildProblemsMuted(p0: MutableCollection<BuildProblemInfo>, p1: MuteInfo, p2: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildProbablyHanging(p0: SRunningBuild, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildProblemResponsibleAssigned(p0: MutableCollection<BuildProblemInfo>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildFailing(p0: SRunningBuild, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyTestsMuted(p0: MutableCollection<STest>, p1: MuteInfo, p2: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun getDisplayName(): String {
        return "Flowdock notificator"
    }

    override fun notifyBuildFailedToStart(p0: SRunningBuild, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun getNotificatorType(): String {
        return "flowdockNotificator"
    }

    override fun notifyTestsUnmuted(p0: MutableCollection<STest>, p1: MuteInfo, p2: SUser?, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyBuildStarted(p0: SRunningBuild, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyResponsibleAssigned(p0: SBuildType, p1: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyResponsibleAssigned(p0: TestNameResponsibilityEntry?, p1: TestNameResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    override fun notifyResponsibleAssigned(p0: MutableCollection<TestName>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.sendNotification()
    }

    fun sendNotification(){
        LOG.info("Notification handled")
    }
}