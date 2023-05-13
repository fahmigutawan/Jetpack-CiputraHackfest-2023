package com.example.hackfestciputra2023.viewmodel.pick_location

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hackfestciputra2023.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PickLocationViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel() {
    val showShouldGoToSettingDialog = mutableStateOf(false)
    val userLong = mutableStateOf(.0)
    val userLat = mutableStateOf(.0)
}