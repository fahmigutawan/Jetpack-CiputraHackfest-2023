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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackfestciputra2023.R
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.viewmodel.home.HomeViewModel
import com.ngikut.u_future.component.AppTextInputNormal

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val userProfileState = viewModel.userProfileState.collectAsState()

    Column {
        Column(Modifier.background(color = AppColor.primary400)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                Arrangement.SpaceBetween
            ) {
                Row {
                    Surface(shape = CircleShape) {
                        Icon(Icons.Default.Person, null)
                    }
                    Column {
                        userProfileState.value.data?.let {
                            AppText(text = "Hi, ${it.data.name}", style = AppType.h4Semibold)
                        }
                        AppText(text = "Selamat Datang!", style = AppType.subheading3)
                    }
                }
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.grey50) {
                    Icon(Icons.Default.Notifications, null, tint = AppColor.primary400)
                }
            }
            AppTextInputNormal(
                placeHolder = "Apa yang kamu butuhkan?",
                value = viewModel.searchQuery,
                onValueChange = { viewModel.searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 8.dp)
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .height(100.dp)
                    .background(color = AppColor.grey50)
            )
            Spacer(Modifier.height(10.dp))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), Arrangement.Center
        ) {
            Column {
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.primary400) {
                    Icon(Icons.Default.LocationOn, null)
                }
                AppText(text = "Di sekitarmu", style = AppType.subheading3)
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.primary400) {
                    Icon(painterResource(R.drawable.three_user), null)
                }
                AppText(text = "Di sekitarmu", style = AppType.subheading3)
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Surface(shape = RoundedCornerShape(7.dp), color = AppColor.primary400) {
                    Icon(Icons.Default.AccessTime, null)
                }
                AppText(text = "Di sekitarmu", style = AppType.subheading3)
            }
        }
    }
}