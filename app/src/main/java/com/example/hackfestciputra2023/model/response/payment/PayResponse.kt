package com.example.hackfestciputra2023.model.response.payment

import com.example.hackfestciputra2023.model.response.base.MetaResponse

data class PayResponse(
    val meta:MetaResponse,
    val data:PayDataResponse
)

data class PayDataResponse(
    val invoice_url:String
)
