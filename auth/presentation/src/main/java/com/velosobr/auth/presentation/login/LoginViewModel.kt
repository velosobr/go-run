@file:OptIn(ExperimentalFoundationApi::class)

package com.velosobr.auth.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.auth.domain.AuthRepository
import com.velosobr.auth.domain.UserDataValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow().onEach { email ->
            val isValidEmail = userDataValidator.isValidEmail(email.toString())
            state = state.copy(
                isEmailValid = isValidEmail,
                canLogin = isValidEmail && state.passwordValidationState.isValidPassword && !state.isLoggingIn
            )
        }.launchIn(viewModelScope)

        state.password.textAsFlow().onEach { password ->
            val passwordValidationState = userDataValidator.validatePassword(password.toString())
            state = state.copy(
                passwordValidationState = passwordValidationState,
                canLogin = state.isEmailValid && passwordValidationState.isValidPassword && !state.isLoggingIn
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginClick -> login()
            is LoginAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            else -> Unit
        }
    }

    private fun login() {

        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                state.email.text.toString(),
                state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)
        }
    }
}
