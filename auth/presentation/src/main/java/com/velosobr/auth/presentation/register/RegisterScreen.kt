@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package com.velosobr.auth.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.core.presentation.designsystem.GoRunTheme
import com.velosobr.core.presentation.designsystem.components.GradientBackground
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterActionRoot(
    onSignInClick: () -> Unit,
    onSuccessFulRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel(),
) {

    RegisterScreen(
        state = viewModel.state,
        onAction = viewModel::onAction

    )

}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
        ) {

        }


    }

}

@Preview
@Composable
private fun RegisterActionRootScreenPreview() {

    GoRunTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {}

        )

    }

}