package com.example.hackfestciputra2023

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.example.hackfestciputra2023.component.AppBottomBar
import com.example.hackfestciputra2023.component.AppSnackbar
import com.example.hackfestciputra2023.screen.bayar.BayarScreen
import com.example.hackfestciputra2023.screen.bayar.BayarScreenSuccess
import com.example.hackfestciputra2023.screen.home.HomeScreen
import com.example.hackfestciputra2023.screen.login.LoginScreen
import com.example.hackfestciputra2023.screen.login.PostLoginState
import com.example.hackfestciputra2023.screen.map.MapDetailScreen
import com.example.hackfestciputra2023.screen.onboarding.OnboardingScreen
import com.example.hackfestciputra2023.screen.pick_location.PickLocationScreen
import com.example.hackfestciputra2023.screen.product_service.ProductServiceAroundScreen
import com.example.hackfestciputra2023.screen.product_service.ProductServiceDetailScreen
import com.example.hackfestciputra2023.screen.register.RegisterScreen
import com.example.hackfestciputra2023.screen.splash.SplashScreen
import com.example.hackfestciputra2023.ui.theme.AppColor
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
            navController.addOnDestinationChangedListener { _, destination, _ ->
                rootViewmodel.currentRoute.value = destination.route ?: ""

                rootViewmodel.showBottombar.value = when (destination.route ?: "") {
                    NavRoute.HOME.name -> true
                    NavRoute.PROFILE.name -> true
                    NavRoute.BAYAR.name -> true
                    else -> false
                }
            }

            SwipeRefresh(state = swipeRefreshState, onRefresh = { /*TODO*/ }) {
                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        AppSnackbar(hostState = it)
                    },
                    bottomBar = {
                        if (rootViewmodel.showBottombar.value) {
                            AppBottomBar(
                                onClick = { route -> navController.navigate(route) },
                                currentRoute = rootViewmodel.currentRoute.value
                            )
                        }
                    },
                    floatingActionButton = {
                        if (rootViewmodel.showBottombar.value) {
                            FloatingActionButton(
                                modifier = Modifier.shadow(2.dp, CircleShape),
                                onClick = {
                                    navController.navigate(NavRoute.BAYAR.name)
                                },
                                backgroundColor = AppColor.primary400
                            ) {
                                Icon(
                                    painter = rememberAsyncImagePainter(model = R.drawable.bottombar_bayar_icon),
                                    contentDescription = "",
                                    tint = AppColor.grey50
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true
                ) {
                    NavHost(
                        modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
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
                            HomeScreen(navController)
                        }

                        composable(NavRoute.BAYAR.name) {
                            BayarScreen(navController = navController)
                        }

                        composable(NavRoute.PROFILE.name) {

                        }

                        composable(NavRoute.USER_PICK_LOCATION.name) {
                            PickLocationScreen(
                                navController = navController,
                                showSnackbar = showSnackbar,
                                changeLoadingState = changeLoadingState
                            )
                        }

                        composable(NavRoute.PRODUCT_SERVICE_AROUND.name) {
                            ProductServiceAroundScreen(navController = navController)
                        }

                        composable(
                            "${NavRoute.PRODUCT_SERVICE_DETAIL.name}/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            ProductServiceDetailScreen(navController, id)
                        }

                        composable(NavRoute.BAYAR_COMPLETED.name) {
                            BayarScreenSuccess(navController = navController)
                        }

                        composable(NavRoute.PRODUCT_SERVICE_MOST_REQUESTED.name) {

                        }

                        composable(NavRoute.PRODUCT_SERVICE_OPEN24.name) {

                        }

                        composable(
                            "${NavRoute.MAP_DETAIL.name}/lat={lat}&long={long}",
                            arguments = listOf(
                                navArgument("lat"){
                                    type = NavType.FloatType
                                },
                                navArgument("long"){
                                    type = NavType.FloatType
                                }
                            )
                        ){
                            val lat = (it.arguments?.getFloat("lat") ?: 0f).toDouble()
                            val long = (it.arguments?.getFloat("long") ?: 0f).toDouble()

                            MapDetailScreen(
                                navController = navController,
                                pedagang_lat = lat,
                                pedagang_long = long
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