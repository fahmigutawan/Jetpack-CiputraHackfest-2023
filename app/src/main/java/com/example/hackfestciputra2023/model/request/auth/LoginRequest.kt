package com.example.hackfestciputra2023.model.request.auth

data class LoginRequest(
    val phone_number: String,
    val password: String
)
