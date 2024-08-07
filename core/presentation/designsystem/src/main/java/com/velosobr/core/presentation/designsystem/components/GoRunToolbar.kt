@file:OptIn(ExperimentalMaterial3Api::class)

package com.velosobr.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.velosobr.core.presentation.designsystem.GoRunTheme
import com.velosobr.core.presentation.designsystem.LogoIcon

@Composable
fun GoRunToolbar(
    showBackButton: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    menuItems: List<DropDownMenuItem> = emptyList(),
    onMenuItemClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null,
) {
    val isDropDownOpen by rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    startContent?.invoke()
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
        },
    )

}

@Preview
@Composable
private fun GoRunToolbarPreview() {
    GoRunTheme {
        GoRunToolbar(
            title = "Title",
            showBackButton = false,
            modifier = Modifier.fillMaxWidth(),
            startContent = {
                Icon(imageVector = LogoIcon, contentDescription = null)
            }
        )
    }
}