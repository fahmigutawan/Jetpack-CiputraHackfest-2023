package com.example.hackfestciputra2023.data.remote_source

object HttpEndpoint {
    const val BASE_URL = "https://hackfest.adityaariizkyy.my.id/api/v1"
    const val LOGIN = "$BASE_URL/user/login"
    const val REGISTER = "$BASE_URL/user/register"
    const val USER_LOCATION = "$BASE_URL/user/location"
    const val GET_USER_PICKING_LOCATION_STATUS = "$BASE_URL/user/location/status"
    const val USER_PROFILE = "$BASE_URL/user/profile"
    const val BUSINESS_RECOMMENDATION = "$BASE_URL/business/query"
    const val BUSINESS_DETAILS = "$BASE_URL/business"
}