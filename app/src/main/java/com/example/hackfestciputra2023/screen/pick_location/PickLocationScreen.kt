package com.example.hackfestciputra2023.screen.pick_location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.pick_location.PickLocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.DragState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PickLocationScreen(
    navController: NavController,
    showSnackbar: (String) -> Unit,
    changeLoadingState:(Boolean) -> Unit
) {
    val viewModel = hiltViewModel<PickLocationViewModel>()
    val singapore = LatLng(viewModel.userLat.value, viewModel.userLong.value)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationPermission =
        rememberPermissionState(permission = android.Manifest.permission.ACCESS_COARSE_LOCATION)
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val markerState = rememberMarkerState()
    val startLoading = remember{ mutableStateOf(false) }
    val addLocationState = viewModel.addLocationState.collectAsState()

    LaunchedEffect(key1 = addLocationState.value) {
        when (addLocationState.value) {
            is Resource.Error -> {
                startLoading.value = false
                showSnackbar(addLocationState.value.message.toString())
                changeLoadingState(false)
            }

            is Resource.Loading -> {
                if(startLoading.value){
                    changeLoadingState(true)
                }
            }

            is Resource.Success -> {
                addLocationState.value.data?.let {
                    delay(2000)
                    navController.backQueue.clear()
                    navController.navigate(NavRoute.HOME.name)
                    startLoading.value = false
                    changeLoadingState(false)
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        locationPermission.launchPermissionRequest()
    }

    if (locationPermission.status.isGranted) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    viewModel.userLat.value = it.latitude
                    viewModel.userLong.value = it.longitude

                    markerState.position = LatLng(it.latitude, it.longitude)

                    coroutineScope.launch {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(
                                    singapore,
                                    12.5f,
                                    0f,
                                    0f
                                )
                            ),
                            durationMs = 1000
                        )
                    }
                }
            }

        LaunchedEffect(key1 = true) {
            viewModel.showShouldGoToSettingDialog.value = false
        }
    } else {
        viewModel.showShouldGoToSettingDialog.value = true
    }

    if (viewModel.showShouldGoToSettingDialog.value) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(AppColor.grey50)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppText(
                        text = "Untuk melanjutkan, anda harus menyetujui aplikasi untuk mengakses lokasi anda. Nyalakan lokasi anda secara manual",
                        style = AppType.subheading2,
                        textAlign = TextAlign.Center
                    )
                    AppButton(onClick = {
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + context.getPackageName())
                        )

                        context.startActivity(intent)
                    }, text = "Buka Setting")
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.grey50)
            ) {
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    onClick = {
                        viewModel.addLocation()
                    },
                    text = "Pilih lokasi ini"
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                markerState.position = it
            }
        ) {
            Marker(
                state = markerState,
                title = "Your location",
                draggable = true
            )
        }
    }
}