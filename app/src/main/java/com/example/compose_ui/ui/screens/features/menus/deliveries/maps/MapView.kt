package com.example.compose_ui.ui.screens.features.menus.deliveries.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.bases.ContainerPage
import com.example.compose_ui.ui.cores.data.model.UiState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

fun Context.findActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapView() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    var currentLocation by rememberSaveable {
        mutableStateOf(LatLng(0.0, 0.0))
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 10f)
    }

    LaunchedEffect(currentLocation) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 10f)
    }

    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    LaunchedEffect(permissionState) {
        isLoading = true
        context.findActivity()?.let {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(it)
        }

        val allPermissionsRevoked =
            permissionState.permissions.size == permissionState.revokedPermissions.size

        val permissionsToRequest = permissionState.permissions.filter {
            !it.status.isGranted
        }
        if (permissionsToRequest.isNotEmpty()) permissionState.launchMultiplePermissionRequest()

        if (allPermissionsRevoked) {
            isLoading = false
        } else {
            context.findActivity()?.let {
                val accuracy = Priority.PRIORITY_HIGH_ACCURACY
                if (permissionState.allPermissionsGranted) {
                    fusedLocationProviderClient.getCurrentLocation(
                        accuracy, CancellationTokenSource().token,
                    ).addOnSuccessListener { location ->
                        location?.run {
                            currentLocation = LatLng(latitude, longitude)
                        }
                        isLoading = false
                    }.addOnFailureListener { _ ->
                        isLoading = false
                    }
                } else {
                    isLoading = false
                }
            }
        }
    }

    ContainerPage(uiState = UiState(isLoading = isLoading)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "Viet Name",
                snippet = "Marker in Singapore"
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MapPreview() {
    MapView()
}