package com.example.hackfestciputra2023.viewmodel.product_service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.business.GetBusinessResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductServiceAroundViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val jasaRecommendation = MutableStateFlow<Resource<GetBusinessResponse>>(Resource.Loading())
    val produkRecommendation = MutableStateFlow<Resource<GetBusinessResponse>>(Resource.Loading())

    private fun getJasaRecommendation(){
        viewModelScope.launch {
            repository.getBusinessRecommendation("Jasa").collect{
                jasaRecommendation.value = it
            }
        }
    }

    private fun getProductRecommendation(){
        viewModelScope.launch {
            repository.getBusinessRecommendation("Produk").collect{
                produkRecommendation.value = it
            }
        }
    }

    init {
        getProductRecommendation()
        getJasaRecommendation()
    }
}