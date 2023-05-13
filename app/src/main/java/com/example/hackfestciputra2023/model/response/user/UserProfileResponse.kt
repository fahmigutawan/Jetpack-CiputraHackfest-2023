package com.example.hackfestciputra2023.model.response.user

import com.example.hackfestciputra2023.model.response.base.MetaResponse

data class UserProfileResponse(
    val meta: MetaResponse,
    val data: UserProfileDataResponse
)

data class UserProfileDataResponse(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val deleted_at: String?,
    val name: String,
    val phone_number: String,
    val address: String
)