package com.dms.pmsandroid.feature.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dms.pmsandroid.R
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.inject

class FCMService : FirebaseMessagingService() {

    companion object {
        private val CHANNEL_ID = "PMS_FCM"
        private val CHANNEL_NAME = "PMS_NOTIFICATION"
    }

    private val localStorage: SharedPreferenceStorage by inject()
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        localStorage.saveInfo(p0, "device_token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val notificationManager = NotificationManagerCompat.from(applicationContext)

        val builder: NotificationCompat.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                    val channel = NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                }
                NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            } else {
                NotificationCompat.Builder(applicationContext)
            }

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(message.notification?.title)
            setContentText(message.notification?.body)
        }

        val notification = builder.build()
        notificationManager.notify(1, notification)

    }

}