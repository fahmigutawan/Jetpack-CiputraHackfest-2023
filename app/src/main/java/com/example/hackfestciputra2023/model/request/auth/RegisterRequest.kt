package com.example.hackfestciputra2023.model.request.auth

data class RegisterRequest(
    val phone_number:String,
    val password:String,
    val address:String,
    val name:String
)
