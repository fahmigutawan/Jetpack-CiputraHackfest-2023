package com.example.hackfestciputra2023.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.hackfestciputra2023.ui.theme.AppColor

@Composable
fun AppSnackbar(hostState: SnackbarHostState) {
    SnackbarHost(hostState = hostState) {
        Snackbar(
            snackbarData = it,
            shape = RoundedCornerShape(4.dp),
            backgroundColor = AppColor.grey200,
            contentColor = AppColor.grey800,
            actionColor = AppColor.primary400
        )
    }
}