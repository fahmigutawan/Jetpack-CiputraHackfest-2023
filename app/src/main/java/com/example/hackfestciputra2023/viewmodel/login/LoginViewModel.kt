package com.example.hackfestciputra2023.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.auth.AuthResponse
import com.example.hackfestciputra2023.model.response.location.GetLocationPickingStatusResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var phoneNumber by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    val loginState = MutableStateFlow<Resource<AuthResponse>>(Resource.Loading())
    val userMapPickingStatusState =
        MutableStateFlow<Resource<GetLocationPickingStatusResponse>>(Resource.Loading())

    fun login() {
        viewModelScope.launch {
            repository.login(
                phone_number = phoneNumber,
                password = password
            ).collect {
                loginState.value = it
            }
        }
    }

    fun getUserLocationPickingStatus() {
        viewModelScope.launch {
            repository.getUserLocationPickingStatus().collect {
                userMapPickingStatusState.value = it
            }
        }
    }

    suspend fun saveToken(token: String) {
        repository.setToken(token)
    }
}