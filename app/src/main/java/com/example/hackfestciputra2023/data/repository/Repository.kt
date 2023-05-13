package com.example.hackfestciputra2023.data.repository

import com.example.hackfestciputra2023.data.datastore_source.DatastoreSource
import com.example.hackfestciputra2023.data.firebase_source.FirebaseSource
import com.example.hackfestciputra2023.data.remote_source.RemoteSource
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val datastoreSource: DatastoreSource,
    private val firebaseSource: FirebaseSource
) {

}