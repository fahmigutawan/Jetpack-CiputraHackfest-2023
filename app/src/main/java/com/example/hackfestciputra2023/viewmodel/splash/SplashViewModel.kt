package com.example.hackfestciputra2023.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){
    fun getToken(onRetrieved:(String) -> Unit){
        viewModelScope.launch{
            repository.getToken().collectLatest{
                onRetrieved(it)
            }
        }
    }
}