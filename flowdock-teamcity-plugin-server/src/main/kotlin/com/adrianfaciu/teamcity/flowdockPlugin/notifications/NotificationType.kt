package com.adrianfaciu.teamcity.flowdockPlugin.notifications

enum class NotificationType {
    BuildFailed,
    BuildFailing,
    BuildSuccessful,
    BuildHanging,
    BuildStarted,
    BuildFailedToStart,
    BuildProblemsUnmuted,
    ResponsibleChanged,
    BuildProblemResponsibleChanged,
    BuildProblemsMuted,
    BuildProblemResponsibleAssigned,
    TestsMuted,
    TestsUnmuted,
    ResponsibleAssigned,
    LabelingFailed
}