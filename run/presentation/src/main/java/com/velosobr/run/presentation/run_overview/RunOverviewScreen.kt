@file:OptIn(ExperimentalMaterial3Api::class)

package com.velosobr.run.presentation.run_overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.core.presentation.designsystem.AnalyticsIcon
import com.velosobr.core.presentation.designsystem.GoRunTheme
import com.velosobr.core.presentation.designsystem.LogoIcon
import com.velosobr.core.presentation.designsystem.LogoutIcon
import com.velosobr.core.presentation.designsystem.RunIcon
import com.velosobr.core.presentation.designsystem.components.DropDownMenuItem
import com.velosobr.core.presentation.designsystem.components.GoRunFloatingActionButton
import com.velosobr.core.presentation.designsystem.components.GoRunScaffold
import com.velosobr.core.presentation.designsystem.components.GoRunToolbar
import com.velosobr.run.presentation.R
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
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    GoRunScaffold(

        topAppBar = {
            GoRunToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.GoRun),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownMenuItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics)
                    ),
                    DropDownMenuItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    )

                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            GoRunFloatingActionButton(
                icon = RunIcon,
                onClick = { onAction(RunOverviewAction.OnStartClick) })
        }
    ) {

    }

}

@Preview
@Composable
fun RunOverviewScreenPreview() {
    GoRunTheme {
        RunOverviewScreen(
            onAction = {},

            )
    }
}