package com.example.hackfestciputra2023.screen.product_service_around

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType

@Composable
fun ProductServiceAroundScreen() {
    val products = listOf("Makanan", "Minuman", "Peralatan", "Lainnya")

    Column {
        Row(Modifier.padding(start = 10.dp)) {
            Surface(shape = CircleShape) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.ArrowBack, null)
                }
            }
            Spacer(Modifier.width(5.dp))
            AppText(text = "Di sekitarmu", style = AppType.h3)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 8.dp)
        ) {
            AppButton(onClick = { /*TODO*/ }) {
                AppText(text = "Produk", style = AppType.h5, color = AppColor.grey50)
            }
            AppButton(onClick = { /*TODO*/ }, backgroundColor = AppColor.grey50) {
                AppText(text = "Jasa", style = AppType.h5, color = AppColor.primary400)
            }
        }
        LazyRow {
            item {
                Spacer(Modifier.width(15.dp))
            }
            items(products) { product ->
                AppButton(onClick = { /*TODO*/ }) {
                    AppText(text = product, style = AppType.h5)
                }
                Spacer(Modifier.width(6.dp))
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}