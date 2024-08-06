@file:OptIn(ExperimentalMaterial3Api::class)

package com.velosobr.core.presentation.designsystem.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.velosobr.core.presentation.designsystem.GoRunTheme

@Composable
fun GoRunToolbar(
    title: String,
    showBackButton: Boolean,
    menuItems: List<DropDownMenuItem> = emptyList(),
    onMenuItemClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: @Composable () -> Unit,
    modifier : Modifier = Modifier,
) {

}

@Preview
@Composable
private fun GoRunToolbarPreview() {
    GoRunTheme {
        GoRunToolbar(
            title = "Title",
            showBackButton = true,
            startContent = {}
        )
    }
}