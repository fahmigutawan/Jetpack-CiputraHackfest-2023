package com.example.hackfestciputra2023.di

import com.example.hackfestciputra2023.data.datastore_source.DatastoreSource
import com.example.hackfestciputra2023.data.firebase_source.FirebaseSource
import com.example.hackfestciputra2023.data.remote_source.RemoteSource
import com.example.hackfestciputra2023.data.repository.Repository
import com.example.hackfestciputra2023.di.datastore.DatastoreModule
import com.example.hackfestciputra2023.di.firebase.FirebaseModule
import com.example.hackfestciputra2023.di.remote.RemoteModule
import com.google.firebase.database.core.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(
    includes = [
        RemoteModule::class,
        DatastoreModule::class,
        FirebaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideRepository(
        firebaseSource: FirebaseSource,
        remoteSource: RemoteSource,
        datastoreSource: DatastoreSource
    ) = Repository(
        remoteSource = remoteSource,
        datastoreSource = datastoreSource,
        firebaseSource = firebaseSource
    )
}