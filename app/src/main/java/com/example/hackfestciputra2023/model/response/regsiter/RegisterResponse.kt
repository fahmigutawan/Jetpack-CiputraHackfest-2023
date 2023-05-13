package com.example.hackfestciputra2023.model.response.regsiter

import com.example.hackfestciputra2023.model.response.base.MetaResponse

data class RegisterResponse(
    val meta:MetaResponse,
    val data:RegisterDataResponse
)

data class RegisterDataResponse(
    val token:String
)
