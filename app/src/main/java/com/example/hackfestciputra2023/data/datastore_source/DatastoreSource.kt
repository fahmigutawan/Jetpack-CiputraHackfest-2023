package com.example.hackfestciputra2023.data.datastore_source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.datastore: DataStore<Preferences> by preferencesDataStore("settings")

class DatastoreSource @Inject constructor(
    private val context:Context
) {
    val datastore = context.datastore

    suspend fun setFirstTimeState(firstTime:Boolean){
        datastore.edit {
            it[booleanPreferencesKey("isFirstTime")] = firstTime
        }
    }

    fun getFirstTimeState() = datastore.data.map {
        it[booleanPreferencesKey("isFirstTime")] ?: true
    }

    suspend fun setToken(token:String){
        datastore.edit {
            it[stringPreferencesKey("accessToken")] = token
        }
    }

    fun getToken() = datastore.data.map {
        it[stringPreferencesKey("accessToken")] ?: ""
    }
}