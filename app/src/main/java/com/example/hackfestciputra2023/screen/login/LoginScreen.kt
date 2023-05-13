package com.example.hackfestciputra2023.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTextButton
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.login.LoginViewModel
import com.ngikut.u_future.component.AppTextInputNormal
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navController: NavController, showSnackbar: (String) -> Unit) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val visualTransformation = if (viewModel.isPasswordVisible)
        VisualTransformation.None else PasswordVisualTransformation()
    val allNotFilled = remember {
        derivedStateOf {
            viewModel.phoneNumber.isEmpty() || viewModel.password.isEmpty()
        }
    }
    val loginState = viewModel.loginState.collectAsState()

    LaunchedEffect(key1 = loginState.value) {
        when(loginState.value) {
            is Resource.Error -> {/*TODO*/}
            is Resource.Loading -> {/*TODO*/}
            is Resource.Success -> {
                loginState.value.data?.let {
                    viewModel.saveToken(it.data.token)
                    delay(2000)
                    navController.backQueue.clear()
                    navController.navigate(NavRoute.POSTLOGIN.name)
                }
            }
        }
    }

    Column(Modifier.padding(horizontal = 20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(70.dp), contentAlignment = Alignment.Center
        ) {
            AppText(text = "DynaMeet", style = AppType.h2, color = AppColor.primary400)
        }
        AppText(text = "Masuk", style = AppType.h3)
        AppText(text = "Halo, Selamat Datang!", style = AppType.subheading2, color = AppColor.grey500)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AppTextInputNormal(
                Modifier.fillMaxWidth(), placeHolder = "Nomor Telepon",
                value = viewModel.phoneNumber, onValueChange = { viewModel.phoneNumber = it },
                textStyle = AppType.subheading3
            )
            AppTextInputNormal(
                Modifier.fillMaxWidth(), placeHolder = "Kata sandi",
                value = viewModel.password, onValueChange = { viewModel.password = it },
                trailingIcon = {
                    IconButton(onClick = { viewModel.isPasswordVisible = !viewModel.isPasswordVisible }) {
                        Icon(
                            imageVector = if (viewModel.isPasswordVisible) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            null
                        )
                    }
                },
                visualTransformation = visualTransformation,
                textStyle = AppType.subheading3
            )
        }
        AppTextButton(onClick = { /*TODO*/ }) {
            AppText(text = "Lupa password?", style = AppType.body2, color = AppColor.grey500)
        }
        Spacer(Modifier.height(20.dp))
        AppButton(
            Modifier.fillMaxWidth(),
            onClick = {
                if (allNotFilled.value) {
                    showSnackbar("Masukkan semua data dengan benar")
                } else {
                    viewModel.login()
                }
            }
        ) {
            AppText(text = "Login", style = AppType.h4, color = AppColor.grey50)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            AppText(
                text = "Belum memiliki akun?", style = AppType.subheading3,
                color = AppColor.grey500
            )
            AppTextButton(onClick = { navController.navigate(NavRoute.REGISTER.name) } ) {
                AppText(text = "Daftar", style = AppType.subheading3, color = AppColor.primary400)
            }
        }
    }
}