package com.prabhat.activitylifecycle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
//from another broadcast is sent
//sendBroadCast("TEST_ACTION")
class TestReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "TEST_ACTION"){
            print("Recived test action")
        }
    }
}