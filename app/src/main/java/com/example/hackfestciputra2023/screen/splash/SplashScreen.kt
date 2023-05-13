package com.example.hackfestciputra2023.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.splash.SplashViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = hiltViewModel<SplashViewModel>()

    LaunchedEffect(key1 = true){
        viewModel.getToken { token ->
            if(token.isEmpty()){
                navController.backQueue.clear()
                navController.navigate(NavRoute.ONBOARDING.name)
            }else{
                navController.backQueue.clear()
                navController.navigate(NavRoute.HOME.name)
            }
        }
    }

    /*TODO UI HERE*/
}