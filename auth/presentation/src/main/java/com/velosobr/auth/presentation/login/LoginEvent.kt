package com.velosobr.auth.presentation.login

import com.velosobr.core.presentation.ui.UiText

sealed interface LoginEvent {

    data object LoginSuccess : LoginEvent
    data class Error(val error: UiText) : LoginEvent
}
