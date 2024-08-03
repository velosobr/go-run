package com.velosobr.run.presentation.run_overview

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.velosobr.core.presentation.designsystem.GoRunTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewRoot(
    viewModel: RunOverviewViewModel = koinViewModel()
) {
    RunOverviewScreen(
        onAction = viewModel::onAction
    )

}

@Composable
private fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit
) {

}

@Preview
@Composable
fun RunOverviewScreenPreview() {
    GoRunTheme {
        RunOverviewScreen(
            onAction = {}
        )
    }
}