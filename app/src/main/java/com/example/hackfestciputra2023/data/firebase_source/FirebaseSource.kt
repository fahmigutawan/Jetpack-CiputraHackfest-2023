package com.example.hackfestciputra2023.data.firebase_source

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class FirebaseSource @Inject constructor(
    private val realtimeDb:FirebaseDatabase
) {
}