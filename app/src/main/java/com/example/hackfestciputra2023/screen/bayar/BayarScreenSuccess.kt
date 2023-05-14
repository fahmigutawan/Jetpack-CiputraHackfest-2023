package com.example.hackfestciputra2023.screen.bayar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hackfestciputra2023.R
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType

@Composable
fun BayarScreenSuccess(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.grey50)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.Center
                ) {
                    AppButton(
                        onClick = { /*TODO*/ },
                        text = "Beranda",
                        textColor = AppColor.primary400,
                        backgroundColor = AppColor.grey50,
                        borderWidth = 2.dp,
                        borderColor = AppColor.primary400
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    AppButton(
                        onClick = { /*TODO*/ },
                        text = "Beri ulasan"
                    )
                }
            }
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            Arrangement.Center, Alignment.CenterHorizontally
        ) {
            Image(painterResource(R.drawable.illustration), null)
            Spacer(Modifier.height(14.dp))
            AppText(text = "Yeay, selamat!", style = AppType.h1, color = AppColor.primary400)
            Spacer(Modifier.height(8.dp))
            AppText(
                text = "Kamu telah berhasil \n melakukan pembayaran",
                style = AppType.subheading1
            )
        }
    }
}