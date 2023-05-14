package com.example.hackfestciputra2023.viewmodel.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.model.response.chat.SendChatResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    val chatValue = mutableStateOf("")
    val chatState = MutableStateFlow<Resource<SendChatResponse>>(Resource.Loading())
    val listOfChat  = mutableStateListOf<Pair<String,String>>()

    fun sendChat(job:String){
        viewModelScope.launch {
            repository.sendChat(chatValue.value, job).collect{
                chatState.value = it
            }
        }
    }
}