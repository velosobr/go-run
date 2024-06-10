package com.velosobr.auth.presentation.intro

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.velosobr.core.presentation.designsystem.GoRunTheme

@Composable
fun IntroScreenRoot(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    IntroScreen(onAction = { action ->
        when (action) {
            IntroAction.OnSignInClick -> onSignInClick()
            IntroAction.OnSIgnUpCLick -> onSignUpClick()
        }
    }
}

@Composable
fun IntroScreen(
    onAction: (IntroAction) -> Unit
) {
    // TODO()
}
@Preview
@Composable
private fun IntroScreenPreview() {
    GoRunTheme {
        IntroScreen(onAction = {})
    }
}