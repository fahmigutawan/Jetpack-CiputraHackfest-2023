package com.example.hackfestciputra2023.data.remote_source

import com.example.hackfestciputra2023.model.request.login.LoginRequest
import com.example.hackfestciputra2023.model.request.register.RegisterRequest
import com.example.hackfestciputra2023.model.response.auth.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val client:HttpClient
) {
    fun register(request:RegisterRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.REGISTER)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }

    fun login(request: LoginRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }

}