package com.example.hackfestciputra2023.viewmodel.register

import androidx.lifecycle.ViewModel
import com.example.hackfestciputra2023.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
}