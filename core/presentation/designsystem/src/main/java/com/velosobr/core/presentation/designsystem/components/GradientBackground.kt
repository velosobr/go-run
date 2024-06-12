package com.velosobr.core.presentation.designsystem.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.core.presentation.designsystem.GoRunTheme

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    hasToolbar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthPx = with(density) {
        configuration.screenWidthDp.dp.toPx()
    }

    val smallDimension = minOf(configuration.screenWidthDp, configuration.screenHeightDp)
}

@Preview
@Composable
private fun GradientBackgroundPreview() {
    GoRunTheme {
        GradientBackground(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }

}