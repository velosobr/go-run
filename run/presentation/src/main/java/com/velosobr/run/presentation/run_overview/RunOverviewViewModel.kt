package com.velosobr.run.presentation.run_overview

import androidx.lifecycle.ViewModel

class RunOverviewViewModel(

) : ViewModel() {


    fun onAction(action: RunOverviewAction) {
        when (action) {
            is RunOverviewAction.OnStartClick -> {

            }

            is RunOverviewAction.OnLogoutClick -> {

            }

            is RunOverviewAction.OnAnalyticsClick -> {

            }
        }
    }

}