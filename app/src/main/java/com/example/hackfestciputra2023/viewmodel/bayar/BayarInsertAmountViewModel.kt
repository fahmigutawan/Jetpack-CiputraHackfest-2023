package com.example.hackfestciputra2023.viewmodel.bayar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.payment.PayResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BayarInsertAmountViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel(){
    val amountValue = mutableStateOf("")

    val prosesBayarState = MutableStateFlow<Resource<PayResponse>>(Resource.Loading())

    fun prosesPembayaran(menu:String){
        viewModelScope.launch {
            repository.processPay(
                Integer.parseInt(amountValue.value),
                "Pembayaran untuk $menu"
            ).collect{
                prosesBayarState.value = it
            }
        }
    }
}