package com.example.hackfestciputra2023.data.remote_source

import com.example.hackfestciputra2023.model.request.auth.LoginRequest
import com.example.hackfestciputra2023.model.request.auth.RegisterRequest
import com.example.hackfestciputra2023.model.request.location.AddLocationRequest
import com.example.hackfestciputra2023.model.request.payment.PayRequest
import com.example.hackfestciputra2023.model.response.auth.AuthResponse
import com.example.hackfestciputra2023.model.response.business.GetBusinessDetailsResponse
import com.example.hackfestciputra2023.model.response.business.GetBusinessResponse
import com.example.hackfestciputra2023.model.response.chat.SendChatResponse
import com.example.hackfestciputra2023.model.response.location.AddLocationResponse
import com.example.hackfestciputra2023.model.response.location.GetLocationPickingStatusResponse
import com.example.hackfestciputra2023.model.response.payment.PayResponse
import com.example.hackfestciputra2023.model.response.user.UserProfileResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val client: HttpClient
) {
    fun register(request: RegisterRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.REGISTER)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun login(request: LoginRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun addLocation(request: AddLocationRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.USER_LOCATION)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AddLocationResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getUserLocationPickingStatus() = getResponse {
        val res = client.get {
            url(HttpEndpoint.GET_USER_PICKING_LOCATION_STATUS)
            contentType(ContentType.Application.Json)
        }.body<GetLocationPickingStatusResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getUserProfile() = getResponse {
        val res = client.get {
            url(HttpEndpoint.USER_PROFILE)
            contentType(ContentType.Application.Json)
        }.body<UserProfileResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getBusinessRecommendation(type: String) = getResponse {
        val res = client.get {
            url("${HttpEndpoint.BUSINESS_RECOMMENDATION}?type=$type")
            contentType(ContentType.Application.Json)
        }.body<GetBusinessResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getBusinessDetails(id: String) = getResponse {
        val res = client.get {
            url("${HttpEndpoint.BUSINESS_DETAILS}/$id")
            contentType(ContentType.Application.Json)
        }.body<GetBusinessDetailsResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun processPay(request: PayRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.PAY)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<PayResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun sendChat(message:String, job:String) = getResponse {
        val res = client.get {
            url("${HttpEndpoint.SEND_CHAT}?message=$message&job=$job")
            contentType(ContentType.Application.Json)
        }.body<SendChatResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }
}