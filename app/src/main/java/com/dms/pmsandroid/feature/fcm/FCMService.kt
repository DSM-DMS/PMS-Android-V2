package com.dms.pmsandroid.feature.fcm

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.notification.ProvideNotificationApi
import com.google.firebase.messaging.FirebaseMessagingService
import org.koin.android.ext.android.inject

class FCMService: FirebaseMessagingService() {

    private val localStorage: SharedPreferenceStorage by inject()
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        localStorage.saveInfo(p0,"device_token")
    }


}