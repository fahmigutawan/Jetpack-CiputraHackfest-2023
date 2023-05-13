package com.example.hackfestciputra2023.model.response.location

import com.example.hackfestciputra2023.model.response.base.MetaResponse

data class GetLocationPickingStatusResponse(
    val meta:MetaResponse,
    val data:GetLocationPickingStatusDataResponse
)

data class GetLocationPickingStatusDataResponse(
    val has_pick_location:Boolean
)
