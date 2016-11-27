package com.adrianfaciu.teamcity.flowdockPlugin.core

import com.adrianfaciu.teamcity.flowdockPlugin.notifications.NotificationType
import com.adrianfaciu.teamcity.flowdockPlugin.util.logInfoMessage
import jetbrains.buildServer.Build
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

class FlowdockNotificator(val notificationManager: FlowdockManager, val builder: NotificationBuilder) : Notificator {
    constructor(notificationManager: FlowdockManager, builder: NotificationBuilder, notificatorRegistry: NotificatorRegistry) : this(notificationManager, builder) {
        notificatorRegistry.register(this)
    }

    fun register(){
        logInfoMessage("Initialized flowdock notificator")
    }

    override fun getDisplayName(): String {
        return "Flowdock notificator"
    }

    override fun getNotificatorType(): String {
        return "flowdockNotificator"
    }

    override fun notifyBuildProblemsUnmuted(p0: MutableCollection<BuildProblemInfo>, p1: MuteInfo, p2: SUser?, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyLabelingFailed(p0: Build, p1: VcsRoot, p2: Throwable, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyResponsibleChanged(p0: SBuildType, p1: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyResponsibleChanged(p0: TestNameResponsibilityEntry?, p1: TestNameResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyResponsibleChanged(p0: MutableCollection<TestName>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyBuildProblemResponsibleChanged(p0: MutableCollection<BuildProblemInfo>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyBuildProblemsMuted(p0: MutableCollection<BuildProblemInfo>, p1: MuteInfo, p2: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyBuildProblemResponsibleAssigned(p0: MutableCollection<BuildProblemInfo>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyTestsMuted(p0: MutableCollection<STest>, p1: MuteInfo, p2: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyBuildFailedToStart(p0: SRunningBuild, p1: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyTestsUnmuted(p0: MutableCollection<STest>, p1: MuteInfo, p2: SUser?, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyResponsibleAssigned(p0: SBuildType, p1: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyResponsibleAssigned(p0: TestNameResponsibilityEntry?, p1: TestNameResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.handleEvent()
    }

    override fun notifyResponsibleAssigned(p0: MutableCollection<TestName>, p1: ResponsibilityEntry, p2: SProject, p3: MutableSet<SUser>) {
        this.handleEvent()
    }


    override fun notifyBuildProbablyHanging(build: SRunningBuild, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildHanging, build, users)
    }

    override fun notifyBuildFailing(build: SRunningBuild, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildFailing, build, users)
    }

    override fun notifyBuildStarted(build: SRunningBuild, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildStarted, build, users)
    }

    override fun notifyBuildSuccessful(build: SRunningBuild, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildSuccessful, build, users)
    }

    override fun notifyBuildFailed(build: SRunningBuild, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildFailed, build, users)
    }

    fun handleEvent(type: NotificationType, build: SRunningBuild, users: MutableSet<SUser>){
        logInfoMessage("Notification handled")

        val notification = this.builder.createNotification(type, build, users)
        this.notificationManager.sendNotification(notification)
    }

    fun handleEvent() {
        logInfoMessage("Ignore this notiications for now")
    }
}