package com.example.hackfestciputra2023.screen.bayar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.BayarPesananItem
import com.example.hackfestciputra2023.model.dummy.DummyBayarPesananItem
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BayarScreen(
    navController: NavController
) {
    val listOfTopBtnSection = listOf("Berjalan", "Riwayat")
    val topBtnContainerWidth = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val dummyItems = listOf(
        DummyBayarPesananItem(
            "Rujak cingur",
            "Sedang dibuat",
            "Belakang UC",
            -7.286260, 112.629993
        ),
        DummyBayarPesananItem(
            "Pecel",
            "Sedang dalam perjalanan",
            "Sebelah indomaret ciputra",
            -7.285909, 112.631164
        )
    )

    Column {
        AppText(modifier = Modifier.padding(20.dp),text = "Bayar Pesanan", style = AppType.h3)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .shadow(5.dp, shape = RoundedCornerShape(Int.MAX_VALUE.dp))
                .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                .background(AppColor.grey50)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .onSizeChanged {
                        density.run {
                            topBtnContainerWidth.value = it.width.toDp()
                        }
                    }
            ) {
                listOfTopBtnSection.forEachIndexed { index, s ->
                    Box(
                        modifier = Modifier
                            .width(topBtnContainerWidth.value / 2)
                            .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                            .background(if (pagerState.currentPage == index) AppColor.primary400 else AppColor.grey50)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = rememberRipple(
                                    bounded = true,
                                    color = AppColor.grey800
                                ),
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        AppText(
                            modifier = Modifier.padding(16.dp),
                            text = s,
                            style = AppType.h5,
                            color = if (pagerState.currentPage == index) AppColor.grey50 else AppColor.primary400
                        )
                    }
                }
            }
        }

        HorizontalPager(
            count = listOfTopBtnSection.size,
            state = pagerState,
            userScrollEnabled = false
        ) { index ->
            when(index){
                0 -> {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)){
                        items(dummyItems){
                            BayarPesananItem(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp), item = it)
                        }

                        item{
                            Spacer(modifier = Modifier)
                        }
                    }
                }

                1 -> {

                }
            }
        }
    }
}