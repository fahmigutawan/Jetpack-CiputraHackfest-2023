package com.example.hackfestciputra2023.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.login.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun PostLoginState(
    navController: NavController
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val getUserLocationPickingStatusState = viewModel.userMapPickingStatusState.collectAsState()

    LaunchedEffect(key1 = getUserLocationPickingStatusState.value){
        when(getUserLocationPickingStatusState.value){
            is Resource.Error -> {
                navController.backQueue.clear()
                navController.navigate(NavRoute.HOME.name)
            }
            is Resource.Loading -> {/*TODO*/}
            is Resource.Success -> {
                getUserLocationPickingStatusState.value.data?.let {
                    if(it.data.has_pick_location){
                        navController.backQueue.clear()
                        navController.navigate(NavRoute.HOME.name)
                    }else{
                        navController.backQueue.clear()
                        navController.navigate(NavRoute.USER_PICK_LOCATION.name)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        viewModel.getUserLocationPickingStatus()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = AppColor.primary400)
    }
}