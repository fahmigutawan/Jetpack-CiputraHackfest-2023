package com.example.hackfestciputra2023

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hackfestciputra2023.component.AppSnackbar
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
            val navController = rememberNavController()
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

            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    AppSnackbar(hostState = it)
                },
            ) {

            }
        }
    }
}

@HiltAndroidApp
class MainApplication : Application()