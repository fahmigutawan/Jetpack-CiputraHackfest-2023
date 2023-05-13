package com.example.hackfestciputra2023.model.response.business

import com.example.hackfestciputra2023.model.response.base.MetaResponse
import com.example.hackfestciputra2023.model.response.base.SingleBusinessResponse

data class GetBusinessResponse(
    val meta:MetaResponse,
    val data:List<SingleBusinessResponse>
)
