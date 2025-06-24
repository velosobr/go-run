package com.velosobr.run.presentation.active_run

import com.velosobr.core.presentation.ui.UiText

sealed interface ActiveRunEvent {
    data class Error(val error: UiText) : ActiveRunEvent
    data object RunSaved : ActiveRunEvent
}