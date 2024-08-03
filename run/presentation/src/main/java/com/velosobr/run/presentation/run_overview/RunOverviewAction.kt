package com.velosobr.run.presentation.run_overview

sealed class RunOverviewAction {

    data object OnStartClick : RunOverviewAction()

    data object OnLogoutClick : RunOverviewAction()

    data object OnAnalyticsClick : RunOverviewAction()
}