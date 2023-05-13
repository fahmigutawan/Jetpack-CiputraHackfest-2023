package com.example.hackfestciputra2023.model.response.auth

import com.example.hackfestciputra2023.model.response.base.MetaResponse

data class AuthResponse(
    val meta:MetaResponse,
    val data:AuthDataResponse
)

data class AuthDataResponse(
    val token:String
)
