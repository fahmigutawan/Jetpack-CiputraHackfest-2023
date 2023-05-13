package com.example.hackfestciputra2023.screen.register

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.viewmodel.register.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<RegisterViewModel>()
}