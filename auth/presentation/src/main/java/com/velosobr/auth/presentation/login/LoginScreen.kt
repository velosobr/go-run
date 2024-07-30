@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.velosobr.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.velosobr.auth.presentation.R
import com.velosobr.auth.presentation.register.RegisterAction
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
fun LoginScreenRoot(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is LoginEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context, event.error.asString(context), Toast.LENGTH_LONG
                ).show()
            }

            LoginEvent.LoginSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context, R.string.login_success, Toast.LENGTH_LONG
                ).show()

                onLoginSuccess()
            }
        }
    }

    LoginScreen(state = viewModel.state, onAction = { action ->
        when (action) {
            is LoginAction.OnLoginClick -> viewModel.onAction(LoginAction.OnLoginClick)
            is LoginAction.OnSignUpClick -> onSignUpClick()
            else -> Unit
        }
        viewModel.onAction(action)
    })
}

@Composable
private fun LoginScreen(
    state: LoginState, onAction: (LoginAction) -> Unit
) {

    GradientBackground {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 32.dp)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.loggin_in),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = stringResource(id = R.string.gorun_wellcome_text),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(48.dp))

                GoRunTextField(
                    state = state.email,
                    startIcon = EmailIcon,
                    endIcon = null,
                    hint = stringResource(id = R.string.example_email),
                    title = stringResource(id = R.string.email),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                GoRunPasswordTextField(
                    state = state.password,
                    isPasswordVisible = state.isPasswordVisible,
                    onTogglePasswordVisibility = { onAction(LoginAction.OnTogglePasswordVisibilityClick) },
                    hint = stringResource(id = R.string.password),
                    title = stringResource(id = R.string.password),
                )

                Spacer(modifier = Modifier.height(32.dp))

                GoRunActionButton(
                    text = stringResource(id = R.string.login),
                    isLoading = state.isLoggingIn,
                    enabled = state.canLogin,
                    onClick = {
                        onAction(LoginAction.OnLoginClick)
                    },
                )

                Spacer(modifier = Modifier.height(16.dp))


            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = 16.dp,
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.dont_have_an_account) + " ",
                    style = TextStyle(
                        fontFamily = Poppins, color = GorunGray
                    )
                )
                val annotatedString = buildAnnotatedString {
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.sign_up)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = Poppins
                        )
                    ) {
                        append(stringResource(id = R.string.sign_up))
                    }
                }
                ClickableText(text = annotatedString, onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "clickable_text", start = offset, end = offset
                    ).firstOrNull()?.let {
                        onAction(LoginAction.OnSignUpClick)
                    }
                })
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    GoRunTheme {
        LoginScreen(state = LoginState(), onAction = {})
    }
}