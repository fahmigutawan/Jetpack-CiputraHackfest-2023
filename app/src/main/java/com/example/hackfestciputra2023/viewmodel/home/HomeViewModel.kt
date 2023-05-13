package com.example.hackfestciputra2023.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.user.UserProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val userProfileState = MutableStateFlow<Resource<UserProfileResponse>>(Resource.Loading())
    var searchQuery by mutableStateOf("")

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            repository.getUserProfile().collect {
                userProfileState.value = it
            }
        }
    }
}