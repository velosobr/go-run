@file:OptIn(ExperimentalFoundationApi::class)

package com.velosobr.core.presentation.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.velosobr.core.presentation.designsystem.CheckIcon
import com.velosobr.core.presentation.designsystem.EmailIcon
import com.velosobr.core.presentation.designsystem.GoRunTheme

@Composable
fun GoRunTextField(
    state: TextFieldState,
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    additionalInfo: String? = null,
) {

}

@Preview
@Composable
fun GoRunTextFieldPreview() {
    GoRunTheme {
        GoRunTextField(
            state = rememberTextFieldState(),
            startIcon = EmailIcon,
            endIcon = CheckIcon,
            hint = "example@mail.com",
            title = "Email",
            additionalInfo = "Must be a valid email address",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}