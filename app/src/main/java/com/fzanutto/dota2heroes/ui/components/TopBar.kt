package com.fzanutto.dota2heroes.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BaseTopBar(
    titleText: String = "",
    scrollBehavior: TopAppBarScrollBehavior? = null,
    actions: @Composable (RowScope.() -> Unit) = {},
    navigationIcon: @Composable () -> Unit = {},
) {
    SmallTopAppBar(
        title = { Text(titleText, color = Color.White) },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFF74942)),
        actions = actions,
        navigationIcon = navigationIcon
    )
}
