package com.example.hackfestciputra2023.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hackfestciputra2023.model.dummy.DummyBayarPesananItem
import com.example.hackfestciputra2023.model.dummy.DummyProductServiceItem
import com.example.hackfestciputra2023.model.response.base.SingleBusinessResponse
import com.example.hackfestciputra2023.model.response.base.Testimony
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType

@Composable
fun ProductServiceItem(
    modifier: Modifier = Modifier,
    item: SingleBusinessResponse,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(AppColor.grey50)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(color = AppColor.grey800, bounded = true),
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(AppColor.primary300)
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = item.link_photo,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    AppText(text = item.name, style = AppType.h5)

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                            .background(if (item.type == "Produk") AppColor.danger50 else AppColor.primary50),
                        contentAlignment = Alignment.Center
                    ) {
                        AppText(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
                            text = item.type,
                            style = AppType.h5
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = "",
                            tint = AppColor.grey500
                        )
                        AppText(
                            text = item.region,
                            style = AppType.subheading3,
                            color = AppColor.grey500
                        )
                    }
                }
            }

            AppIconButton(
                imageVector = Icons.Default.Favorite,
                onClick = { /*TODO*/ },
                backgroundColor = AppColor.grey300
            )
        }
    }
}

@Composable
fun BayarPesananItem(
    modifier: Modifier = Modifier,
    item: DummyBayarPesananItem,
    lihatMapClicked: (DummyBayarPesananItem) -> Unit,
    bayarClicked: (DummyBayarPesananItem) -> Unit
) {
    Box(
        modifier = modifier
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(AppColor.grey50)
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(AppColor.primary300)
                    )

                    Column {
                        AppText(text = item.name, style = AppType.h5)
                        AppText(
                            text = item.status,
                            style = AppType.subheading3,
                            color = AppColor.success400
                        )
                        AppText(
                            text = item.lokasi,
                            style = AppType.subheading3,
                            color = AppColor.grey500
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AppButton(
                        onClick = { lihatMapClicked(item) },
                        text = "Lihat Maps",
                        backgroundColor = AppColor.primary100,
                        textColor = AppColor.primary400
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    AppButton(onClick = { bayarClicked(item) }, text = "Bayar sekarang")
                }
            }
        }
    }
}

@Composable
fun DummyProductServiceItem(
    modifier: Modifier = Modifier,
    item: DummyProductServiceItem,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(AppColor.grey50)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(color = AppColor.grey800, bounded = true),
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(AppColor.primary300)
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    AppText(text = item.nama, style = AppType.h5)

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                            .background(if (item.type == "Produk") AppColor.danger50 else AppColor.primary50),
                        contentAlignment = Alignment.Center
                    ) {
                        AppText(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
                            text = item.type,
                            style = AppType.h5
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = "",
                            tint = AppColor.grey500
                        )
                        AppText(
                            text = item.lokasi,
                            style = AppType.subheading3,
                            color = AppColor.grey500
                        )
                    }
                }
            }

            AppIconButton(
                imageVector = Icons.Default.Favorite,
                onClick = { /*TODO*/ },
                backgroundColor = AppColor.grey300
            )
        }
    }
}

@Composable
fun ProductServiceUlasanItem(modifier: Modifier = Modifier, item: Testimony) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(AppColor.grey100)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                AppText(text = item.user.name, style = AppType.body1)
            }

            AppText(text = item.comentar, style = AppType.body2, color = AppColor.grey600)
        }
    }
}