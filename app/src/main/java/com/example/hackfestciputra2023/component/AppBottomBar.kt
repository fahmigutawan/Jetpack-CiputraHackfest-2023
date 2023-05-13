package com.example.hackfestciputra2023.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hackfestciputra2023.ui.theme.AppColor

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    onClick: (route: String) -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColor.grey50),
        backgroundColor = AppColor.grey50
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColor.grey50)
        ) {

        }
    }
}