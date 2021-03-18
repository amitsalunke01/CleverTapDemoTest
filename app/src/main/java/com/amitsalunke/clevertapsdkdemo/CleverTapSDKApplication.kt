package com.amitsalunke.clevertapsdkdemo

import android.app.Application
import android.app.NotificationManager
import com.clevertap.android.sdk.CleverTapAPI

class CleverTapSDKApplication : Application() {

    companion object{
        var cleverTapDefaultInstance: CleverTapAPI? = null
    }

    override fun onCreate() {
        super.onCreate()
        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)

        // not getting reference for createNotificationChannel so not able to check for notification
        //createNotificationChannel
        //cleverTapDefaultInstance.createNotificationChannel(applicationContext,"Channel1","Channel Name","Demo Description", NotificationManager.IMPORTANCE_MAX,true)

    }
}