package com.example.hackfestciputra2023.screen.home

import com.example.hackfestciputra2023.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppIconButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTextButton
import com.example.hackfestciputra2023.component.ProductServiceItem
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.home.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ngikut.u_future.component.AppTextInputNormal

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val getProfileState = viewModel.userProfileState.collectAsState()
    val jasaRecommendation = viewModel.jasaRecommendation.collectAsState()
    val productRecommendation = viewModel.produkRecommendation.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary400),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    when (getProfileState.value) {
                        is Resource.Error -> {
                            /*TODO*/
                        }

                        is Resource.Loading -> {
                            /*TODO*/
                        }

                        is Resource.Success -> {
                            getProfileState.value.data?.let {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .clip(CircleShape)
                                            .background(AppColor.grey500),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(32.dp),
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "",
                                            tint = AppColor.grey50
                                        )
                                    }
                                    Column {
                                        AppText(
                                            text = "Hi, ${it.data.name}",
                                            style = AppType.h4,
                                            color = AppColor.grey50
                                        )
                                        AppText(
                                            text = "Selamat datang",
                                            style = AppType.subheading3,
                                            color = AppColor.grey50
                                        )
                                    }
                                }
                            }
                        }
                    }

                    AppIconButton(
                        imageVector = Icons.Default.Notifications,
                        onClick = { /*TODO*/ },
                        tint = AppColor.primary400,
                        shape = CircleShape,
                        backgroundColor = AppColor.grey50
                    )
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp))
                    .background(AppColor.primary400),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    AppTextInputNormal(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        placeHolder = "Apa yang kamu butuhkan?",
                        value = viewModel.searchValue.value,
                        onValueChange = {
                            viewModel.searchValue.value = it
                        }
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        val state = rememberPagerState()
                        HorizontalPager(state = state, count = 3) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .padding(20.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(AppColor.primary100)
                            )
                        }
                        HorizontalPagerIndicator(
                            pagerState = state,
                            activeColor = AppColor.primary400
                        )
                    }
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary600),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppColor.grey50)
                ) {
                    val threeMenuWidth = remember { mutableStateOf(0.dp) }
                    val density = LocalDensity.current

                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 32.dp, start = 32.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .onSizeChanged {
                                density.run {
                                    threeMenuWidth.value = (it.width / 3).toDp()
                                }
                            },
                        verticalAlignment = Alignment.Top
                    ) {
                        HomeInfoMenuItems.values().forEach { item ->
                            AppTextButton(
                                modifier = Modifier.width(threeMenuWidth.value),
                                onClick = { navController.navigate(route = item.route) }
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(AppColor.primary400),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(16.dp),
                                            painter = rememberAsyncImagePainter(model = item.iconId),
                                            contentDescription = "",
                                            tint = AppColor.grey50
                                        )
                                    }

                                    AppText(
                                        text = item.word,
                                        style = AppType.subheading3,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(text = "Rekomendasi Jasa", style = AppType.h3)
                AppTextButton(onClick = { /*TODO*/ }) {
                    AppText(text = "Lihat semua", style = AppType.body2, color = AppColor.grey500)
                }
            }
        }

        when (jasaRecommendation.value) {
            is Resource.Error -> {/*TODO*/
            }

            is Resource.Loading -> {/*TODO*/
            }

            is Resource.Success -> {
                jasaRecommendation.value.data?.let {
                    item {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            it.data.forEach {
                                ProductServiceItem(
                                    modifier = Modifier.width(350.dp),
                                    item = it,
                                    onClick = {})
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(text = "Rekomendasi Produk", style = AppType.h3)
                AppTextButton(onClick = { /*TODO*/ }) {
                    AppText(text = "Lihat semua", style = AppType.body2, color = AppColor.grey500)
                }
            }
        }

        when (productRecommendation.value) {
            is Resource.Error -> {/*TODO*/
            }

            is Resource.Loading -> {/*TODO*/
            }

            is Resource.Success -> {
                productRecommendation.value.data?.let {
                    item {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            it.data.forEach {
                                ProductServiceItem(
                                    modifier = Modifier.width(350.dp),
                                    item = it,
                                    onClick = {})
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class HomeInfoMenuItems(
    val iconId: Int,
    val word: String,
    val route: String
) {
    Sekitarmu(
        R.drawable.home_location_icon,
        "Di sekitarmu",
        NavRoute.PRODUCT_SERVICE_AROUND.name
    ),
    Dibutuhkan(
        R.drawable.home_3user_icon,
        "Paling Dibutuhkan",
        NavRoute.PRODUCT_SERVICE_MOST_REQUESTED.name
    ),
    Buka24Jam(
        R.drawable.home_24hour_icon,
        "Buka 24 Jam",
        NavRoute.PRODUCT_SERVICE_OPEN24.name
    )
}