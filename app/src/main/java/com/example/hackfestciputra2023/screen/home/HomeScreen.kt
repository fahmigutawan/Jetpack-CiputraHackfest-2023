package com.example.hackfestciputra2023.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackfestciputra2023.R
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.viewmodel.home.HomeViewModel
import com.ngikut.u_future.component.AppTextInputNormal
import java.nio.file.WatchEvent

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val userProfileState = viewModel.userProfileState.collectAsState()

    Column {
        Column(
            Modifier.background(
                color = AppColor.primary400,
                shape = RoundedCornerShape(bottomEnd = 35.dp, bottomStart = 35.dp)
            )
        ) {
            Spacer(Modifier.height(18.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 23.dp),
                Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape) {
                        Icon(Icons.Default.Person, null)
                    }
                    Spacer(Modifier.width(8.dp))
                    Column {
                        userProfileState.value.data?.let {
                            AppText(
                                text = "Hi, ${it.data.name}", style = AppType.h4Semibold,
                                color = AppColor.grey50
                            )
                        }
                        AppText(
                            text = "Selamat Datang!", style = AppType.subheading3,
                            color = AppColor.grey50
                        )
                    }
                }
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.grey50) {
                    Icon(
                        Icons.Default.Notifications, null,
                        Modifier
                            .padding(10.dp)
                            .size(30.dp), tint = AppColor.primary400
                    )
                }
            }
            Spacer(Modifier.height(17.dp))
            AppTextInputNormal(
                placeHolder = "Apa yang kamu butuhkan?",
                value = viewModel.searchQuery,
                onValueChange = { viewModel.searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 23.dp)
                    .padding(bottom = 8.dp)
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 23.dp)
                    .height(100.dp)
                    .background(color = AppColor.grey50, shape = RoundedCornerShape(16.dp))
            )
            Spacer(Modifier.height(45.dp))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.primary400) {
                    Icon(Icons.Default.LocationOn, null,
                        Modifier
                            .padding(15.dp)
                            .size(35.dp), tint = AppColor.grey50)
                }
                AppText(text = "Di sekitarmu", style = AppType.subheading3, textAlign = TextAlign.Center)
            }
            Spacer(Modifier.width(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.primary400) {
                    Icon(
                        painterResource(R.drawable.three_user), null,
                        Modifier
                            .padding(15.dp)
                            .size(35.dp), tint = AppColor.grey50
                    )
                }
                AppText(text = "Paling \n Dibutuhkan", style = AppType.subheading3,
                    textAlign = TextAlign.Center)
            }
            Spacer(Modifier.width(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.primary400) {
                    Icon(
                        Icons.Default.AccessTime, null,
                        Modifier
                            .padding(15.dp)
                            .size(35.dp), tint = AppColor.grey50

                    )
                }
                AppText(text = "Buka 24 Jam", style = AppType.subheading3, textAlign = TextAlign.Center)
            }
        }
        Column(Modifier.padding(start = 23.dp)) {
            AppText(text = "Rekomendasi Jasa", style = AppType.h3)
            Spacer(Modifier.height(10.dp))
        }
    }
}