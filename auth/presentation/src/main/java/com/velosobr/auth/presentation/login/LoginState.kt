@file:OptIn(ExperimentalFoundationApi::class)

package com.velosobr.auth.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.velosobr.auth.domain.PasswordValidationState

data class LoginState(
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isLoggingIn: Boolean = false,
    val canLogin: Boolean = false
)