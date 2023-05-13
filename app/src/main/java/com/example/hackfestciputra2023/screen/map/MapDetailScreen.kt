package com.example.hackfestciputra2023.screen.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.R
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.util.bitmapDescriptor
import com.example.hackfestciputra2023.viewmodel.pick_location.PickLocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
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
fun MapDetailScreen(
    navController: NavController,
    pedagang_lat:Double,
    pedagang_long:Double
) {
    val viewModel = hiltViewModel<PickLocationViewModel>()
    val pedagang = LatLng(pedagang_lat, pedagang_long)
    val user = LatLng(viewModel.userLat.value, viewModel.userLong.value)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(user, 10f)
    }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationPermission =
        rememberPermissionState(permission = Manifest.permission.ACCESS_COARSE_LOCATION)
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val markerState = rememberMarkerState()
    val startLoading = remember{ mutableStateOf(false) }
    val addLocationState = viewModel.addLocationState.collectAsState()

    LaunchedEffect(key1 = addLocationState.value) {
        when (addLocationState.value) {
            is Resource.Error -> {
                startLoading.value = false
            }

            is Resource.Loading -> {
                if(startLoading.value){
                }
            }

            is Resource.Success -> {
                addLocationState.value.data?.let {
                    delay(2000)
                    navController.backQueue.clear()
                    navController.navigate(NavRoute.HOME.name)
                    startLoading.value = false
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
                                    pedagang,
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
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = markerState,
                title = "Your location",
                draggable = false,
                icon = bitmapDescriptor(
                    context = context,
                    vectorResId = R.drawable.baseline_circle_24
                )
            )

            Marker(
                state = MarkerState(
                    pedagang
                ),
                title = "Pedagang's location",
                draggable = false
            )
        }
    }
}