@file:OptIn(ExperimentalFoundationApi::class)

package com.velosobr.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.auth.domain.PasswordValidationState
import com.velosobr.auth.domain.UserDataValidator
import com.velosobr.auth.presentation.R
import com.velosobr.core.presentation.designsystem.CheckIcon
import com.velosobr.core.presentation.designsystem.CrossIcon
import com.velosobr.core.presentation.designsystem.EmailIcon
import com.velosobr.core.presentation.designsystem.GoRunTheme
import com.velosobr.core.presentation.designsystem.GorunGray
import com.velosobr.core.presentation.designsystem.Poppins
import com.velosobr.core.presentation.designsystem.components.GoRunActionButton
import com.velosobr.core.presentation.designsystem.components.GoRunPasswordTextField
import com.velosobr.core.presentation.designsystem.components.GoRunTextField
import com.velosobr.core.presentation.designsystem.components.GradientBackground
import com.velosobr.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    onSignInClick: () -> Unit,
    onSuccessFulRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }

            is RegisterEvent.RegistrationSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.registration_successful,
                    Toast.LENGTH_LONG
                ).show()
                onSuccessFulRegistration()
            }
        }
    }
    RegisterScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                RegisterAction.OnLoginClick -> onSignInClick()
                else -> viewModel.onAction(action)
            }
        }
    )

}

@Composable
private fun RegisterScreen(
    state: RegisterState, onAction: (RegisterAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )
            Row {
                Text(
                    text = stringResource(id = R.string.already_have_an_account) + " ",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Poppins, color = GorunGray
                    )
                )
                val annotatedString = buildAnnotatedString {
                    pushStringAnnotation(
                        tag = "clickable_text", annotation = stringResource(id = R.string.login)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = Poppins
                        )
                    ) {
                        append(stringResource(id = R.string.login))
                    }
                }
                ClickableText(text = annotatedString, onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "clickable_text", start = offset, end = offset
                    ).firstOrNull()?.let {
                        onAction(RegisterAction.OnLoginClick)
                    }

                })
            }
            Spacer(modifier = Modifier.height(48.dp))
            GoRunTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = if (state.isEmailValid) {
                    CheckIcon
                } else {
                    null
                },
                hint = stringResource(id = R.string.example_email),
                title = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth(),
                additionalInfo = stringResource(id = R.string.must_be_a_valid_email),
                keyboardType = KeyboardType.Email
            )


            Spacer(modifier = Modifier.height(16.dp))

            GoRunPasswordTextField(
                state = state.password,
                hint = stringResource(id = R.string.password),
                title = stringResource(id = R.string.password),
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordRequirement(
                text = stringResource(
                    id = R.string.at_least_x_characters, UserDataValidator.MIN_PASSWORD_LENGTH
                ), isValid = state.passwordValidationState.hasMinLength
            )
            Spacer(modifier = Modifier.height(4.dp))

            PasswordRequirement(
                text = stringResource(
                    id = R.string.at_least_one_number, UserDataValidator.MIN_PASSWORD_LENGTH
                ), isValid = state.passwordValidationState.hasNumber
            )
            Spacer(modifier = Modifier.height(4.dp))

            PasswordRequirement(
                text = stringResource(
                    id = R.string.contains_lowercase_character,
                    UserDataValidator.MIN_PASSWORD_LENGTH
                ), isValid = state.passwordValidationState.hasLowerCaseCharacter
            )
            Spacer(modifier = Modifier.height(4.dp))

            PasswordRequirement(
                text = stringResource(
                    id = R.string.contains_uppercase_character,
                    UserDataValidator.MIN_PASSWORD_LENGTH
                ), isValid = state.passwordValidationState.hasUpperCaseCharacter
            )
            Spacer(modifier = Modifier.height(32.dp))

            GoRunActionButton(
                text = stringResource(id = R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,

                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                }
            )
        }


    }

}

@Composable
fun PasswordRequirement(
    text: String, isValid: Boolean, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isValid) CheckIcon else CrossIcon,
            contentDescription = null,
            tint = if (isValid) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {

    GoRunTheme {
        RegisterScreen(
            state = RegisterState(
                passwordValidationState = PasswordValidationState(
                    hasMinLength = true,
                    hasNumber = true,
                    hasLowerCaseCharacter = true,
                    hasUpperCaseCharacter = true
                ),
                canRegister = false


            ), onAction = {})

    }

}