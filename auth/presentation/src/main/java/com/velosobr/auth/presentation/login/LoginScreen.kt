package com.velosobr.auth.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.velosobr.auth.presentation.R
import com.velosobr.core.presentation.designsystem.GoRunTheme
import com.velosobr.core.presentation.designsystem.components.GradientBackground
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreenRoot(
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {

}
@Composable
private fun LoginScreen(
    state: LoginState, onAction: (LoginAction) -> Unit
) {

    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ){
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
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
  GoRunTheme {
    LoginScreen(LoginState()) {}
  }
}