package com.example.hackfestciputra2023.screen.product_service_around

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTopBarMidTitle
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.viewmodel.product_service_around.ProductServiceAroundViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductServiceAroundScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<ProductServiceAroundViewModel>()
    val listOfTopBtnSection = listOf("Produk", "Jasa")
    val pagerState = rememberPagerState()
    val products = listOf("Makanan", "Minuman", "Peralatan", "Lainnya")
    val services = listOf("Reparasi", "Vermak Jeans", "Sol Sepatu")
    val listOfProductAndServices = listOf(products, services)
    val topBtnContainerWidth = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppTopBarMidTitle(onBackClicked = { /*TODO*/ }, title = "Di sekitarmu")
        }
    ) {
        Column {
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
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(state = rememberScrollState())
                    ) {
                        listOfProductAndServices[index].let {
                            it.forEach {
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                                        .background(AppColor.primary200)
                                ) {
                                    AppText(
                                        modifier = Modifier.padding(
                                            vertical = 4.dp,
                                            horizontal = 16.dp
                                        ), text = it, style = AppType.h5
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}