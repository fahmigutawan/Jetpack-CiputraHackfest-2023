package com.example.hackfestciputra2023.model.response.chat

import com.example.hackfestciputra2023.model.response.base.MetaResponse

data class SendChatResponse(
    val meta: MetaResponse,
    val data:SendChatDataResponse
)

data class SendChatDataResponse(
    val text:String
)