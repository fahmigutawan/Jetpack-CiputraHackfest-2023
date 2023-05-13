package com.example.hackfestciputra2023.viewmodel.pick_location

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.location.AddLocationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickLocationViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val showShouldGoToSettingDialog = mutableStateOf(false)
    val userLong = mutableStateOf(.0)
    val userLat = mutableStateOf(.0)

    val addLocationState = MutableStateFlow<Resource<AddLocationResponse>>(Resource.Loading())
    fun addLocation() {
        viewModelScope.launch {
            repository.addLocation(userLat.value, userLong.value).collect {
                addLocationState.value = it
            }
        }
    }
}