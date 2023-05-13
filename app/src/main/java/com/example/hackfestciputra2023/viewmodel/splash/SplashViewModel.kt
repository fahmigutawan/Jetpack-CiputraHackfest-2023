package com.example.hackfestciputra2023.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.location.GetLocationPickingStatusResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){
    val userLocationPickingStatusState = MutableStateFlow<Resource<GetLocationPickingStatusResponse>>(Resource.Loading())
    fun getToken(onRetrieved:(String) -> Unit){
        viewModelScope.launch{
            repository.getToken().collectLatest{
                onRetrieved(it)
            }
        }
    }

    fun getUserLocationPickingStatus(){
        viewModelScope.launch {
            repository.getUserLocationPickingStatus().collectLatest {
                userLocationPickingStatusState.value = it
            }
        }
    }
}