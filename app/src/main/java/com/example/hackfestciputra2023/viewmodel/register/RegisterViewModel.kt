package com.example.hackfestciputra2023.viewmodel.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.auth.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val namaValue = mutableStateOf("")
    val alamatValue = mutableStateOf("")
    val noTelpvalue = mutableStateOf("")
    val passValue = mutableStateOf("")
    val showPassValue = mutableStateOf(false)
    val passConfirmValue = mutableStateOf("")
    val showPassConfirmValue = mutableStateOf(false)

    val registerState = MutableStateFlow<Resource<AuthResponse>>(Resource.Loading())

    fun register(){
        viewModelScope.launch {
            repository.register(
                phone_number = noTelpvalue.value,
                password = passValue.value,
                address = alamatValue.value,
                name = namaValue.value
            ).collect{
                registerState.value = it
            }
        }
    }
}