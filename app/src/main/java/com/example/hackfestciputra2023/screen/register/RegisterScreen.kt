package com.example.hackfestciputra2023.screen.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.viewmodel.register.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<RegisterViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp), contentAlignment = Alignment.Center
        ) {
            AppText(text = "DynaMeet", style = AppType.h2, color = AppColor.primary400)
        }

        Column {
            AppText(text = "Daftar", style = AppType.h3Semibold)
            AppText(text = "Buat akun baru", style = AppType.subheading2, color = AppColor.grey500)
        }
    }
}