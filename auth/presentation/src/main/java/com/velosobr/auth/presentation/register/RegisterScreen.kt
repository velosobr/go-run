@file:OptIn(ExperimentalFoundationApi::class)

package com.velosobr.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.auth.presentation.R
import com.velosobr.core.presentation.designsystem.CheckIcon
import com.velosobr.core.presentation.designsystem.EmailIcon
import com.velosobr.core.presentation.designsystem.EyeOpenedIcon
import com.velosobr.core.presentation.designsystem.GoRunTheme
import com.velosobr.core.presentation.designsystem.GorunGray
import com.velosobr.core.presentation.designsystem.LockIcon
import com.velosobr.core.presentation.designsystem.Poppins
import com.velosobr.core.presentation.designsystem.components.GoRunPasswordTextField
import com.velosobr.core.presentation.designsystem.components.GoRunTextField
import com.velosobr.core.presentation.designsystem.components.GradientBackground
import org.koin.androidx.compose.koinViewModel
import java.time.format.TextStyle

@Composable
fun RegisterActionRoot(
    onSignInClick: () -> Unit,
    onSuccessFulRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel(),
) {

    RegisterScreen(
        state = viewModel.state, onAction = viewModel::onAction

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
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.login)
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
                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "click_text",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            onAction(RegisterAction.onLoginClick)
                        }

                    }
                )
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
                    onAction(RegisterAction.onTogglePasswordVisibilityClick)
                },
                modifier = Modifier.fillMaxWidth(),
            )

        }


    }

}

@Preview
@Composable
private fun RegisterActionRootScreenPreview() {

    GoRunTheme {
        RegisterScreen(state = RegisterState(), onAction = {}

        )

    }

}