package com.prabhat.activitylifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.prabhat.activitylifecycle.ui.theme.ActivityLifecycleTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ActivityLifecycleTheme {
                Text("Second Activity")
            }
        }
    }
}