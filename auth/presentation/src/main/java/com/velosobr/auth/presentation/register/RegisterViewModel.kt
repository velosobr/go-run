@file:OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.velosobr.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.auth.domain.AuthRepository
import com.velosobr.auth.domain.UserDataValidator
import com.velosobr.auth.presentation.R
import com.velosobr.core.domain.util.DataError
import com.velosobr.core.domain.util.Result
import com.velosobr.core.presentation.ui.UiText
import com.velosobr.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userDataValidator: UserDataValidator, private val authRepository: AuthRepository
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set
    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow().onEach { email ->
            val isValidEmail = userDataValidator.isValidEmail(email.toString())
            state = state.copy(
                isEmailValid = isValidEmail,
                canRegister = state.passwordValidationState.isValidPassword && isValidEmail && !state.isRegistering
            )
        }.launchIn(viewModelScope)

        state.password.textAsFlow().onEach { password ->
            val passwordValidationState = userDataValidator.validatePassword(password.toString())
            state = state.copy(
                passwordValidationState = passwordValidationState,
                canRegister = state.isEmailValid && passwordValidationState.isValidPassword && !state.isRegistering
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: RegisterAction) {
        when(action) {
            is RegisterAction.OnRegisterClick -> register()
            is RegisterAction.OnTogglePasswordVisibilityClick ->
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            else -> Unit


        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = authRepository.register(
                email = state.email.toString().trim(),
                password = state.password.toString(),

                )
            state = state.copy(isRegistering = false)

            when (result) {
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }

                is Result.Error -> {
                    if (result.error == DataError.NetworkError.CONFLICT)
                        eventChannel.send(RegisterEvent.Error(UiText.StringResource(R.string.error_email_already_registered)))
                    else
                        eventChannel.send(RegisterEvent.Error(result.error.asUiText()))

                }
            }


        }
    }

}