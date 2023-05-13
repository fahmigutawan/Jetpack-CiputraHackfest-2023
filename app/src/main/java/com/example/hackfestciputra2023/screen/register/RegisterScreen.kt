package com.example.hackfestciputra2023.screen.register

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppIconButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTextButton
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.register.RegisterViewModel
import com.ngikut.u_future.component.AppTextInputNormal

@Composable
fun RegisterScreen(
    navController: NavController,
    showSnackbar: (String) -> Unit
) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val allNotFilled = remember {
        derivedStateOf {
            viewModel.namaValue.value.isEmpty()
                    || viewModel.alamatValue.value.isEmpty()
                    || viewModel.noTelpvalue.value.isEmpty()
                    || viewModel.passValue.value.isEmpty()
                    || viewModel.passConfirmValue.value.isEmpty()
        }
    }
    val passwordSame = remember {
        derivedStateOf {
            viewModel.passValue.value == viewModel.passConfirmValue.value
        }
    }
    val registerState = viewModel.registerState.collectAsState()

    LaunchedEffect(key1 = registerState.value){
        when(registerState.value){
            is Resource.Error -> {/*TODO*/}
            is Resource.Loading -> {/*TODO*/}
            is Resource.Success -> {/*TODO*/}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(45.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp), contentAlignment = Alignment.Center
        ) {
            AppText(text = "DynaMeet", style = AppType.h2, color = AppColor.primary400)
        }

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Column {
                AppText(text = "Daftar", style = AppType.h3Semibold)
                AppText(
                    text = "Buat akun baru",
                    style = AppType.subheading2,
                    color = AppColor.grey500
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AppTextInputNormal(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Nama",
                    value = viewModel.namaValue.value,
                    onValueChange = {
                        viewModel.namaValue.value = it
                    }
                )

                AppTextInputNormal(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Alamat",
                    value = viewModel.alamatValue.value,
                    onValueChange = {
                        viewModel.alamatValue.value = it
                    }
                )

                AppTextInputNormal(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Nomor Telepon",
                    value = viewModel.noTelpvalue.value,
                    onValueChange = {
                        viewModel.noTelpvalue.value = it
                    }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                AppTextInputNormal(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Kata Sandi",
                    value = viewModel.passValue.value,
                    onValueChange = {
                        viewModel.passValue.value = it
                    },
                    visualTransformation = if (viewModel.showPassValue.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        AppIconButton(
                            imageVector = if (viewModel.showPassValue.value) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility,
                            onClick = {
                                viewModel.showPassValue.value = !viewModel.showPassValue.value
                            },
                            tint = AppColor.grey500
                        )
                    }
                )

                AppTextInputNormal(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Konfirmasi Kata Sandi",
                    value = viewModel.passConfirmValue.value,
                    onValueChange = {
                        viewModel.passConfirmValue.value = it
                    },
                    visualTransformation = if (viewModel.showPassConfirmValue.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        AppIconButton(
                            imageVector = if (viewModel.showPassConfirmValue.value) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility,
                            onClick = {
                                viewModel.showPassConfirmValue.value =
                                    !viewModel.showPassConfirmValue.value
                            },
                            tint = AppColor.grey500
                        )
                    }
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    when {
                        allNotFilled.value -> {
                            showSnackbar("Masukkan semua data dengan benar")
                        }
                        !passwordSame.value -> {
                            showSnackbar("Masukkan password yang sama")
                        }
                    }
                },
                text = "Daftar"
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                AppText(
                    text = "Sudah memiliki akun?",
                    style = AppType.subheading3,
                    color = AppColor.grey500
                )

                AppTextButton(onClick = {
                    navController.navigate(NavRoute.LOGIN.name)
                }) {
                    AppText(
                        text = "Masuk",
                        style = AppType.subheading3,
                        color = AppColor.primary400
                    )
                }
            }
        }
    }
}