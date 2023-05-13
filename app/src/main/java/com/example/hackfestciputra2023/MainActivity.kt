package com.example.hackfestciputra2023

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hackfestciputra2023.component.AppSnackbar
import com.example.hackfestciputra2023.screen.login.LoginScreen
import com.example.hackfestciputra2023.screen.pick_location.PickLocationScreen
import com.example.hackfestciputra2023.screen.register.RegisterScreen
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.RootViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private lateinit var rootViewmodel: RootViewModel

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            rootViewmodel = viewModel()

            val scaffoldState = rememberScaffoldState()
            val showSnackbar: (message: String) -> Unit = { message ->
                rootViewmodel.snackbarMessage.value = message
                rootViewmodel.snackbarActive.value = true
            }
            val showSnackbarWithAction: (
                message: String,
                actionLabel: String,
                action: (SnackbarData?) -> Unit,
            ) -> Unit = { message, actionLabel, action ->
                rootViewmodel.snackbarActionLabel.value = actionLabel
                rootViewmodel.snackbarAction.value = action
                rootViewmodel.snackbarMessage.value = message
                rootViewmodel.snackbarActive.value = true
            }
            if (rootViewmodel.snackbarActive.value) {
                LaunchedEffect(key1 = true) {
                    val resetSnackbarState = {
                        rootViewmodel.snackbarAction.value = {}
                        rootViewmodel.snackbarActionLabel.value = "Tutup"
                        rootViewmodel.snackbarMessage.value = ""
                        rootViewmodel.snackbarActive.value = false
                    }
                    val snackbarData = scaffoldState.snackbarHostState.showSnackbar(
                        message = rootViewmodel.snackbarMessage.value,
                        actionLabel = rootViewmodel.snackbarActionLabel.value,
                        duration = SnackbarDuration.Short
                    )

                    when (snackbarData) {
                        SnackbarResult.Dismissed -> {
                            resetSnackbarState()
                        }

                        SnackbarResult.ActionPerformed -> {
                            when (rootViewmodel.snackbarActionLabel.value) {
                                "Tutup" -> {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
                                    resetSnackbarState()
                                }

                                else -> {
                                    rootViewmodel.snackbarAction.value(scaffoldState.snackbarHostState.currentSnackbarData)
                                    resetSnackbarState()
                                }
                            }
                        }
                    }
                }
            }

            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    AppSnackbar(hostState = it)
                },
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavRoute.USER_PICK_LOCATION.name
                ) {
                    composable(NavRoute.SPLASH.name) {

                    }

                    composable(NavRoute.LOGIN.name) {
                        LoginScreen(navController = navController)
                    }

                    composable(NavRoute.REGISTER.name) {
                        RegisterScreen(navController = navController, showSnackbar = showSnackbar)
                    }

                    composable(NavRoute.ONBOARDING.name){

                    }

                    composable(NavRoute.HOME.name){

                    }

                    composable(NavRoute.USER_PICK_LOCATION.name){
                        PickLocationScreen()
                    }
                }
            }
        }
    }
}

@HiltAndroidApp
class MainApplication : Application()