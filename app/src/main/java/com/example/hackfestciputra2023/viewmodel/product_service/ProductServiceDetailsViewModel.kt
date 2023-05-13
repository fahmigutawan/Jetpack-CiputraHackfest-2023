package com.example.hackfestciputra2023.viewmodel.product_service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.business.GetBusinessDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductServiceDetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val businessDetails = MutableStateFlow<Resource<GetBusinessDetailsResponse>>(Resource.Loading())

    fun getBusinessDetails(id: Int) {
        viewModelScope.launch {
            repository.getBusinessDetails(id).collect {
                businessDetails.value = it
            }
        }
    }
}