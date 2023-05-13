package com.example.hackfestciputra2023.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.hackfestciputra2023.R
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.util.NavRoute

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String,
    onClick: (route: String) -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColor.grey50),
        backgroundColor = AppColor.grey50
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColor.grey50)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                AppBottomBarItem.values().forEach {
                    IconButton(onClick = {onClick(it.route)}) {
                        Icon(
                            painter = rememberAsyncImagePainter(model = if (currentRoute == it.route) it.iconSelected else it.iconUnselected),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}

enum class AppBottomBarItem(
    val word: String,
    val iconSelected: Int,
    val iconUnselected: Int,
    val route: String
) {
    Home(
        "Beranda",
        R.drawable.bottombar_home_selected,
        R.drawable.bottombar_home_unselected,
        NavRoute.HOME.name
    ),
    Profile(
        "Profil",
        R.drawable.bottombar_profile_selected,
        R.drawable.bottombar_profile_unselected,
        NavRoute.PROFILE.name
    )
}