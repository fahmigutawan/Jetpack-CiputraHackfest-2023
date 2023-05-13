package com.example.hackfestciputra2023.data.repository

import com.example.hackfestciputra2023.data.datastore_source.DatastoreSource
import com.example.hackfestciputra2023.data.firebase_source.FirebaseSource
import com.example.hackfestciputra2023.data.remote_source.RemoteSource
import com.example.hackfestciputra2023.model.request.auth.LoginRequest
import com.example.hackfestciputra2023.model.request.auth.RegisterRequest
import com.example.hackfestciputra2023.model.request.location.AddLocationRequest
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val datastoreSource: DatastoreSource,
    private val firebaseSource: FirebaseSource
) {
    suspend fun setToken(token: String) = datastoreSource.setToken(token)

    fun getToken() = datastoreSource.getToken()
    fun register(phone_number: String, password: String, address: String, name: String) =
        remoteSource.register(
            RegisterRequest(
                phone_number = phone_number,
                password = password,
                address = address,
                name = name
            )
        )
    fun login(phone_number: String, password: String) =
        remoteSource.login(
            LoginRequest(
                phone_number = phone_number,
                password = password,
            )
        )

    fun addLocation(latitude:Double, longitude:Double) = remoteSource.addLocation(
        AddLocationRequest(
            latitude,
            longitude
        )
    )

    fun getUserLocationPickingStatus() = remoteSource.getUserLocationPickingStatus()

    fun getUserProfile() = remoteSource.getUserProfile()
}