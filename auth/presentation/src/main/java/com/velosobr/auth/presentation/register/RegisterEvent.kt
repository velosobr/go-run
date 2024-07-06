package com.velosobr.auth.presentation.register

import com.velosobr.core.presentation.ui.UiText

sealed interface RegisterEvent {

    data object RegistrationSuccess : RegisterEvent
    data class Error(val error: UiText) : RegisterEvent

}