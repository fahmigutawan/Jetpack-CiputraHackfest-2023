package com.example.hackfestciputra2023.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTopBarMidTitle
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.viewmodel.chat.ChatViewModel
import com.ngikut.u_future.component.AppTextInputBasic

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatScreen(
    navController: NavController,
    nama: String,
    job: String
) {
    val viewModel = hiltViewModel<ChatViewModel>()
    val density = LocalDensity.current
    val chatState = viewModel.chatState.collectAsState()
    val chatInputWidth = remember { mutableStateOf(0.dp) }
    val containerWidth = remember { mutableStateOf(0.dp) }
    val btnWidth = remember { mutableStateOf(0.dp) }
    val sendingChat = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = chatState.value) {
        when (chatState.value) {
            is Resource.Error -> {}
            is Resource.Loading -> {}
            is Resource.Success -> {
                if (sendingChat.value) {
                    viewModel.listOfChat.add(
                        "YOU" to (chatState.value.data?.data?.text ?: "")
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
                 AppTopBarMidTitle(onBackClicked = { /*TODO*/ }, title = nama)
        },
        bottomBar = {
            BottomAppBar {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppColor.grey50),
                    backgroundColor = AppColor.grey50
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onSizeChanged {
                                density.run {
                                    containerWidth.value = it.width.toDp()
                                }
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AppTextInputBasic(
                            modifier = Modifier.width(containerWidth.value - 32.dp - btnWidth.value),
                            placeHolder = "Chat anda",
                            value = viewModel.chatValue.value,
                            onValueChange = {
                                viewModel.chatValue.value = it
                            })
                        AppButton(
                            modifier = Modifier.onSizeChanged {
                                density.run {
                                    btnWidth.value = it.width.toDp()
                                }
                            },
                            onClick = {
                                viewModel.listOfChat.add(
                                    "ME" to viewModel.chatValue.value
                                )
                                viewModel.sendChat(job)
                                viewModel.chatValue.value = ""
                                sendingChat.value = true
                            }) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "",
                                tint = AppColor.grey50
                            )
                        }
                    }
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = it.calculateBottomPadding() + 16.dp
                    ),
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.listOfChat.reversed()) {
                    if (it.first == "ME") {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            topEnd = 25.dp,
                                            topStart = 25.dp,
                                            bottomStart = 25.dp
                                        )
                                    )
                                    .background(AppColor.primary100)
                            ) {
                                AppText(
                                    modifier = Modifier.padding(16.dp),
                                    text = it.second,
                                    style = AppType.body2
                                )
                            }
                        }
                    } else if (it.first == "YOU") {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            topEnd = 25.dp,
                                            bottomEnd = 25.dp,
                                            bottomStart = 25.dp
                                        )
                                    )
                                    .background(AppColor.primary100)
                            ) {
                                AppText(
                                    modifier = Modifier.padding(16.dp),
                                    text = it.second,
                                    style = AppType.body2
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}