package com.example.hackfestciputra2023.model.response.base

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
    val testimonies:String?
)
