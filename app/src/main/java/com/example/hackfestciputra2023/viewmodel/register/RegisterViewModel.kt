package com.example.hackfestciputra2023.viewmodel.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hackfestciputra2023.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
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
}