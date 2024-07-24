package com.velosobr.auth.presentation.login

sealed interface LoginAction {

    data object OnTogglePasswordVisibilityClick : LoginAction
    data object OnLoginClick : LoginAction

    data object OnSignUpClick : LoginAction
}
