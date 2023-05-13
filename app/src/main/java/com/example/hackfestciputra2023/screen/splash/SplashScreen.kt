package com.example.hackfestciputra2023.screen.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.splash.SplashViewModel

@Composable
fun SplashScreen(navController: NavController, showSnackbar:(String) -> Unit) {
    val viewModel = hiltViewModel<SplashViewModel>()
    val userLocationPickingStatusState = viewModel.userLocationPickingStatusState.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.getToken { token ->
            if(token.isEmpty()){
                navController.backQueue.clear()
                navController.navigate(NavRoute.ONBOARDING.name)
            }else{
                viewModel.getUserLocationPickingStatus()
            }
        }
    }

    LaunchedEffect(key1 = userLocationPickingStatusState.value){
        when(userLocationPickingStatusState.value){
            is Resource.Error -> {
                showSnackbar(userLocationPickingStatusState.value.message.toString())
            }
            is Resource.Loading -> {/*TODO*/}
            is Resource.Success -> {
                userLocationPickingStatusState.value.data?.let { locationPickedStatus ->
                    viewModel.getToken { token ->
                        if(token.isEmpty()){
                            navController.backQueue.clear()
                            navController.navigate(NavRoute.ONBOARDING.name)
                        }else{
                            if(locationPickedStatus.data.has_pick_location){
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
        }
    }

    /*TODO UI HERE*/
}