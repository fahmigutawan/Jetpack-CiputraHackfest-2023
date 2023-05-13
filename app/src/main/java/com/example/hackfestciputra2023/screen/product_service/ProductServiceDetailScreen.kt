package com.example.hackfestciputra2023.screen.product_service

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTopBarMidTitle
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.viewmodel.product_service.ProductServiceDetailsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductServiceDetailScreen(id: Int) {
    val viewModel = hiltViewModel<ProductServiceDetailsViewModel>()
    val listOfTopBtnSection = listOf("Deskripsi", "Ulasan")
    val topBtnContainerWidth = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val businessDetailsState = viewModel.businessDetails.collectAsState()

    LaunchedEffect(businessDetailsState) {
        viewModel.getBusinessDetails(id)
    }

    Scaffold(
        topBar = {
            AppTopBarMidTitle(onBackClicked = { /*TODO*/ }, title = "Produk")
        },
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
                        text = "Lihat Map",
                        textColor = AppColor.primary400,
                        backgroundColor = AppColor.grey50,
                        borderWidth = 2.dp,
                        borderColor = AppColor.primary400
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    AppButton(
                        onClick = { /*TODO*/ },
                        text = "Pesan Sekarang"
                    )
                }
            }
        }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(AppColor.primary100)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
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

            HorizontalPager(state = pagerState, count = listOfTopBtnSection.size) { index ->
                when (index) {
                    0 -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            when (businessDetailsState.value) {
                                is Resource.Error -> { /*TODO*/ }
                                is Resource.Loading -> { /*TODO*/ }
                                is Resource.Success -> {
                                    businessDetailsState.value.data?.let {
                                        item {
                                            AppText(text = it.data.name, style = AppType.h3)
                                        }
                                        item {
                                            Column {
                                                //nama penjual
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Person,
                                                        contentDescription = "",
                                                        tint = AppColor.grey500
                                                    )
                                                    AppText(
                                                        text = "Pak Joko Wijaya",
                                                        style = AppType.subheading3,
                                                        color = AppColor.grey500
                                                    )
                                                }

                                                //alamat
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.LocationOn,
                                                        contentDescription = "",
                                                        tint = AppColor.grey500
                                                    )
                                                    AppText(
                                                        text = it.data.region,
                                                        style = AppType.subheading3,
                                                        color = AppColor.grey500
                                                    )
                                                }

                                                //jarak
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Send,
                                                        contentDescription = "",
                                                        tint = AppColor.grey500
                                                    )
                                                    AppText(
                                                        text = "0,3 km",
                                                        style = AppType.subheading3,
                                                        color = AppColor.grey500
                                                    )
                                                }
                                            }
                                        }
                                        item {
                                            AppText(
                                                text = it.data.description,
                                                style = AppType.body1,
                                                color = AppColor.grey600
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    1 -> {
                        LazyColumn {
                            when (businessDetailsState.value) {
                                is Resource.Error -> { /*TODO*/ }
                                is Resource.Loading -> { /*TODO*/ }
                                is Resource.Success -> {
                                    businessDetailsState.value.data?.let {
                                        items(it.data.testimonies) {item ->
                                            Column(
                                                Modifier
                                                    .background(color = AppColor.grey100)
                                                    .padding(7.dp)) {
                                                Row {
                                                    Surface(shape = CircleShape) {
                                                        Icon(Icons.Default.Person, null)
                                                    }
                                                    AppText(text = item.user.name, style = AppType.subheading3)
                                                }
                                                AppText(text = item.comentar, style = AppType.body2,
                                                    color = AppColor.grey500)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}