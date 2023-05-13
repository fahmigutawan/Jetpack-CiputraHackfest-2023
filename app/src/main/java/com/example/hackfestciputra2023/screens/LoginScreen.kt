package com.example.hackfestciputra2023.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTextButton
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.ngikut.u_future.component.AppTextInputNormal

@Composable
fun LoginScreen() {
    Column(Modifier.padding(horizontal = 8.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp), Arrangement.Center) {
            AppText(text = "DynaMeet", style = AppType.h1, color = AppColor.primary400)
        }
        AppText(text = "Masuk", style = AppType.h3)
        AppText(text = "Halo, Selamat Datang!", style = AppType.subheading2, color = AppColor.grey500)
        AppTextInputNormal(
            placeHolder = "Nomor Telepon", value = "",
            onValueChange = { }
        )
        AppTextInputNormal(
            placeHolder = "Kata sandi", value = "", 
            onValueChange = { }, trailingIcon = { }
        )
        AppTextButton(onClick = { /*TODO*/ }) {
            AppText(text = "Lupa password?", style = AppType.body2, color = AppColor.grey500)
        }
        Spacer(Modifier.height(20.dp))
        AppButton(
            Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            AppText(text = "Login", style = AppType.h4, color = AppColor.grey50)
        }
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            AppText(
                text = "Belum memiliki akun?", style = AppType.subheading3,
                color = AppColor.grey500)
            AppTextButton(onClick = { /*TODO*/ }) {
                AppText(text = "Daftar", style = AppType.subheading3, color = AppColor.primary400)
            }
        }
    }
}