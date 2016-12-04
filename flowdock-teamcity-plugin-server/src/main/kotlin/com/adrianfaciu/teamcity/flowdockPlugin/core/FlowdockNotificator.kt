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

/**
 * Handle all server events
 */
class FlowdockNotificator(val notificationManager: FlowdockManager, val builder: NotificationBuilder, val projectManager: ProjectManager) : Notificator {
    constructor(notificationManager: FlowdockManager, builder: NotificationBuilder, notificatorRegistry: NotificatorRegistry, projectManager: ProjectManager)
            : this(notificationManager, builder, projectManager) {
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

    override fun notifyBuildProblemsUnmuted(buildProblems: MutableCollection<BuildProblemInfo>, muteInfo: MuteInfo, user: SUser?, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildProblemsUnmuted, buildProblems, users, muteInfo.project)
    }

    override fun notifyResponsibleChanged(tests: MutableCollection<TestName>, responisbility: ResponsibilityEntry, project: SProject, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.ResponsibleChanged, tests, users, project)
    }

    override fun notifyBuildProblemResponsibleChanged(buildProblems: MutableCollection<BuildProblemInfo>, responsibility: ResponsibilityEntry, project: SProject, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildProblemResponsibleChanged, buildProblems, users, project)
    }

    override fun notifyBuildProblemsMuted(buildProblems: MutableCollection<BuildProblemInfo>, muteInfo: MuteInfo, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildProblemsMuted, buildProblems, users, muteInfo.project)
    }

    override fun notifyBuildProblemResponsibleAssigned(buildProblems: MutableCollection<BuildProblemInfo>, responisbility: ResponsibilityEntry, project: SProject, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildProblemResponsibleAssigned, buildProblems, users, project)
    }

    override fun notifyTestsMuted(tests: MutableCollection<STest>, muteInfo: MuteInfo, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.TestsMuted, tests, users, muteInfo.project)
    }

    override fun notifyTestsUnmuted(tests: MutableCollection<STest>, muteInfo: MuteInfo, user: SUser?, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.TestsUnmuted, tests, users, muteInfo.project)
    }

    override fun notifyResponsibleAssigned(tests: MutableCollection<TestName>, responsibility: ResponsibilityEntry, project: SProject, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.ResponsibleAssigned, tests, users, project)
    }

    override fun notifyLabelingFailed(build: Build, vcsRoot: VcsRoot, error: Throwable, users: MutableSet<SUser>) {
        val project = this.projectManager.projects.filter { p -> p.projectId == build.projectId }.first()
        this.handleEvent(NotificationType.LabelingFailed, users, project)
    }

    override fun notifyResponsibleChanged(buildType: SBuildType, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.ResponsibleChanged, users, buildType.project)
    }

    override fun notifyResponsibleChanged(p0: TestNameResponsibilityEntry?, p1: TestNameResponsibilityEntry, project: SProject, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.ResponsibleChanged, users, project)
    }

    override fun notifyResponsibleAssigned(buildType: SBuildType, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.ResponsibleChanged, users, buildType.project)
    }

    override fun notifyResponsibleAssigned(p0: TestNameResponsibilityEntry?, p1: TestNameResponsibilityEntry, project: SProject, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.ResponsibleAssigned, users, project)
    }

    override fun notifyBuildFailedToStart(build: SRunningBuild, users: MutableSet<SUser>) {
        this.handleEvent(NotificationType.BuildFailedToStart, build, users)
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

        val project = this.projectManager.projects.filter { p -> p.projectId == build.projectId }.first()
        val notification = this.builder.createNotification(type, build, users, project)
        this.notificationManager.sendNotification(notification)
    }

    fun <T> handleEvent(type: NotificationType, collection: MutableCollection<T>, users: MutableSet<SUser>, project: SProject?) {
        logInfoMessage("Notification handled generic")

        val notification = this.builder.createNotification(type, collection, users, project)
        this.notificationManager.sendNotification(notification)
    }

    fun handleEvent(type: NotificationType, users: MutableSet<SUser>, project: SProject) {
        logInfoMessage("Notification handled simple")

        val notification = this.builder.createNotification(type, users, project)
        this.notificationManager.sendNotification(notification)
    }
}