package com.example.hackfestciputra2023.di.firebase

import com.example.hackfestciputra2023.data.firebase_source.FirebaseSource
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseSource(
        realtimeDb:FirebaseDatabase
    ) = FirebaseSource(
        realtimeDb = realtimeDb
    )
}