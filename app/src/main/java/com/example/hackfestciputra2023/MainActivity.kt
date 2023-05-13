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
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.screen.home.HomeScreen
import com.example.hackfestciputra2023.screen.login.LoginScreen
import com.example.hackfestciputra2023.screen.login.PostLoginState
import com.example.hackfestciputra2023.screen.onboarding.OnboardingScreen
import com.example.hackfestciputra2023.screen.pick_location.PickLocationScreen
import com.example.hackfestciputra2023.screen.register.RegisterScreen
import com.example.hackfestciputra2023.screen.splash.SplashScreen
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.RootViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private lateinit var rootViewmodel: RootViewModel
    private lateinit var swipeRefreshState: SwipeRefreshState

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            rootViewmodel = viewModel()
            swipeRefreshState =
                rememberSwipeRefreshState(isRefreshing = rootViewmodel.isLoading.value)

            val scaffoldState = rememberScaffoldState()
            val changeLoadingState: (Boolean) -> Unit = {
                rootViewmodel.isLoading.value = it
            }
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

            SwipeRefresh(state = swipeRefreshState, onRefresh = { /*TODO*/ }) {
                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        AppSnackbar(hostState = it)
                    },
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavRoute.SPLASH.name
                    ) {
                        composable(NavRoute.SPLASH.name) {
                            SplashScreen(navController = navController, showSnackbar = showSnackbar)
                        }

                        composable(NavRoute.LOGIN.name) {
                            LoginScreen(
                                navController = navController,
                                showSnackbar = showSnackbar,
                                changeLoadingState = changeLoadingState
                            )
                        }

                        composable(NavRoute.POSTLOGIN.name) {
                            PostLoginState(navController = navController)
                        }

                        composable(NavRoute.REGISTER.name) {
                            RegisterScreen(
                                navController = navController,
                                showSnackbar = showSnackbar,
                                changeLoadingState = changeLoadingState
                            )
                        }

                        composable(NavRoute.ONBOARDING.name) {
                            OnboardingScreen(navController = navController)
                        }

                        composable(NavRoute.HOME.name) {
                            HomeScreen()
                        }

                        composable(NavRoute.USER_PICK_LOCATION.name) {
                            PickLocationScreen(
                                navController = navController,
                                showSnackbar = showSnackbar,
                                changeLoadingState = changeLoadingState
                            )
                        }
                    }
                }
            }
        }
    }
}

@HiltAndroidApp
class MainApplication : Application()