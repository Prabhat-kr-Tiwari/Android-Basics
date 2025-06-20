package com.prabhat.activitylifecycle

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class RunningService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        when (intent?.action) {

            Action.START.toString() -> {
                start()
            }

            Action.STOP.toString() -> {

                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)


    }
    private fun start(){


        val notification = NotificationCompat.Builder(this,"runningchannel")
            .setSmallIcon(R.drawable.main_before)
            .setContentTitle("Run is Active")
            .setContentText("09:23")
            .build()
        startForeground(1,notification)
    }

    enum class Action {
        START, STOP

    }

}