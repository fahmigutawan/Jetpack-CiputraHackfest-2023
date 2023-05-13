package com.example.hackfestciputra2023.di.datastore

import android.content.Context
import com.example.hackfestciputra2023.data.datastore_source.DatastoreSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule{
    @Provides
    @Singleton
    fun provideDatastoreSource(
        @ApplicationContext context: Context
    ) = DatastoreSource(context)
}