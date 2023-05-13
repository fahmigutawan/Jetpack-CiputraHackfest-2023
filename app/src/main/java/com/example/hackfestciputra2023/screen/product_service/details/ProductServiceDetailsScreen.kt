package com.example.hackfestciputra2023.screen.product_service.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hackfestciputra2023.R
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType

@Composable
fun ProductServiceDetailsScreen() {
    Column {
        Row(Modifier.padding(start = 12.dp)) {
            Surface(shape = CircleShape) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.ArrowBack, null)
                }
            }
            Spacer(Modifier.width(5.dp))
            AppText(text = "Produk", style = AppType.h3)
        }
        Image(painterResource(R.drawable.grey_frame), null, Modifier.padding(10.dp))
        Row {
            AppButton(onClick = { /*TODO*/ }, backgroundColor = AppColor.primary400) {
                AppText(text = "Deskripsi", style = AppType.subheading3, color = AppColor.grey50)
            }
            AppButton(onClick = { /*TODO*/ }, backgroundColor = AppColor.grey50) {
                AppText(text = "Deskripsi", style = AppType.subheading3, color = AppColor.primary400)
            }
            AppButton(onClick = { /*TODO*/ }, backgroundColor = AppColor.grey50) {
                AppText(text = "Deskripsi", style = AppType.subheading3, color = AppColor.primary400)
            }
        }
    }
}