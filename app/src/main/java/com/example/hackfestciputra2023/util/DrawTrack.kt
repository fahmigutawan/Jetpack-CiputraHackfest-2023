package com.example.hackfestciputra2023.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun drawTrack(source: String, destination: String, context: Context) {
    try {
        // create a uri
        val uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/$source/$destination")
        // initializing a intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // below line is to set maps package name
        i.setPackage("com.google.android.apps.maps")
        // below line is to set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // start activity
        context.startActivity(i)
    } catch (e: ActivityNotFoundException) {
        // when the google maps is not installed on users device
        // we will redirect our user to google play to download google maps.
        val uri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
        // initializing intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // to start activity
        context.startActivity(i)
    }
}