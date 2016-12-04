package com.adrianfaciu.teamcity.flowdockPlugin.notifications

/**
 * Types of notifications annotated with additional details (text and color)
 * Mapped to the type of events emitted by the server
 */
enum class NotificationType {
    @NotificationDetails("Build failed", "red")
    BuildFailed,

    @NotificationDetails("Build failing", "grey")
    BuildFailing,

    @NotificationDetails("Build successful", "green")
    BuildSuccessful,

    @NotificationDetails("Build hanging", "yellow")
    BuildHanging,

    @NotificationDetails("Build started", "yellow")
    BuildStarted,

    @NotificationDetails("Build failed to start", "red")
    BuildFailedToStart,

    @NotificationDetails("Build problems unmuted", "blue")
    BuildProblemsUnmuted,

    @NotificationDetails("Responsible changed", "blue")
    ResponsibleChanged,

    @NotificationDetails("Build problems responsible changed", "blue")
    BuildProblemResponsibleChanged,

    @NotificationDetails("Build problems muted", "blue")
    BuildProblemsMuted,

    @NotificationDetails("Build problem responsible assigned", "blue")
    BuildProblemResponsibleAssigned,

    @NotificationDetails("Tests muted", "red")
    TestsMuted,

    @NotificationDetails("Tests unmuted", "green")
    TestsUnmuted,

    @NotificationDetails("Responsible assigned", "blue")
    ResponsibleAssigned,

    @NotificationDetails("Labeling failed", "blue")
    LabelingFailed
}

