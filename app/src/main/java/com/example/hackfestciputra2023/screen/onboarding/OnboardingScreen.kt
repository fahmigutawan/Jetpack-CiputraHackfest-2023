package com.example.hackfestciputra2023.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hackfestciputra2023.R
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTextButton
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val onboardingItems = listOf(
        OnboardingData(
            R.drawable.group_8676,
            "Temukan jasa keliling di sekitarmu",
            "Nikmati kemudahan menemukan jasa keliling tanpa harus " +
                    "saling tunggu di tengah ketidakpastian mobilisasi."
        ),
        OnboardingData(
            R.drawable.group_8675,
            "Dapatkan berbagai macam produk UMK",
            "Pesan produk yang Anda inginkan dari berbagai " +
                    "pedagang keliling yang berjualan di sekitarmu."
        ),
        OnboardingData(
            R.drawable.group_55224241,
            "Pilih jenis akunmu!",
            "Jadilah pengguna atau ambil peluang untuk memperluas jangkauan bisnismu."
        )
    )
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var accountType by remember { mutableStateOf(AccountType.USER) }

    Column {
        HorizontalPager(state = pagerState, count = onboardingItems.size) { page ->
            Surface(color = AppColor.primary50) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp, top = 25.dp), Arrangement.End) {
                    if (pagerState.currentPage < onboardingItems.size - 1) {
                        AppTextButton(onClick = { navController.navigate(NavRoute.LOGIN.name) }) {
                            AppText(text = "Skip", style = AppType.h4)
                        }
                    }
                }
                Image(
                    painterResource(onboardingItems[page].imageId), null,
                    Modifier.padding(70.dp)
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Column(Modifier.padding(horizontal = 30.dp)) {
            AppText(text = onboardingItems[pagerState.currentPage].title, style = AppType.h1)
            Spacer(Modifier.height(20.dp))
            AppText(
                text = onboardingItems[pagerState.currentPage].description, style = AppType.subheading1,
                color = AppColor.grey600
            )
            if (pagerState.currentPage == onboardingItems.size - 1) {
                Spacer(Modifier.height(15.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                    AppButton(
                        backgroundColor = if (accountType == AccountType.USER) AppColor.primary50
                                          else AppColor.grey50,
                        borderColor = AppColor.primary100,
                        onClick = { accountType = AccountType.USER }
                    ) {
                        AppText(Modifier.padding(horizontal = 18.dp), text = "Pengguna",
                            style = AppType.h4, color = AppColor.primary400)
                    }
                    Spacer(Modifier.width(22.dp))
                    AppButton(
                        backgroundColor = if (accountType == AccountType.SELLER) AppColor.primary50
                                          else AppColor.grey50,
                        borderColor = AppColor.primary100,
                        onClick = { accountType = AccountType.SELLER }
                    ) {
                        AppText(Modifier.padding(horizontal = 18.dp), text = "Pelaku Usaha",
                            style = AppType.h4, color = AppColor.primary400)
                    }
                }
                AppButton(
                    onClick = { navController.navigate(NavRoute.LOGIN.name) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 17.dp)
                ) {
                    AppText(text = "Lanjutkan", style = AppType.h4, color = AppColor.grey50)
                }
            }
            else {
                Spacer(Modifier.height(90.dp))
            }
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                HorizontalPagerIndicator(
                    pagerState = pagerState, activeColor = AppColor.primary400,
                    inactiveColor = AppColor.grey200
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (pagerState.currentPage > 0) {
                        AppTextButton(
                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        ) {
                            AppText(text = "Kembali", style = AppType.h4)
                        }
                        Spacer(Modifier.width(10.dp))
                    }
                    if (pagerState.currentPage < onboardingItems.size - 1) {
                        AppButton(
                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        ) {
                            AppText(text = "Selanjutnya", style = AppType.h4, color = AppColor.grey50)
                        }
                    }
                }

            }
        }
    }
}