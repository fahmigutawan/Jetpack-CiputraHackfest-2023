package com.example.hackfestciputra2023.model.response.base

import com.example.hackfestciputra2023.model.response.user.UserProfileDataResponse

data class SingleBusinessResponse(
    val id:String,
    val created_at:String,
    val updated_at:String?,
    val deleted_at:String?,
    val name:String,
    val region:String,
    val description:String,
    val is_available:Boolean,
    val close_time:String,
    val open_time:String,
    val type:String,
    val offered:String,
    val latitude:Double,
    val longitude:Double,
    val link_photo:String,
    val testimonies:List<Testimony>
)

data class Testimony(
    val id:String,
    val created_at:String,
    val updated_at:String?,
    val deleted_at:String?,
    val id_business:String,
    val id_user:String,
    val link_photo: String,
    val comentar: String,
    val user: UserProfileDataResponse
)
