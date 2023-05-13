package com.example.hackfestciputra2023.data.repository

import com.example.hackfestciputra2023.data.datastore_source.DatastoreSource
import com.example.hackfestciputra2023.data.firebase_source.FirebaseSource
import com.example.hackfestciputra2023.data.remote_source.RemoteSource
import com.example.hackfestciputra2023.model.request.register.RegisterRequest
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val datastoreSource: DatastoreSource,
    private val firebaseSource: FirebaseSource
) {
    fun register(phone_number: String, password: String, address: String, name: String) =
        remoteSource.register(
            RegisterRequest(
                phone_number = phone_number,
                password = password,
                address = address,
                name = name
            )
        )
}