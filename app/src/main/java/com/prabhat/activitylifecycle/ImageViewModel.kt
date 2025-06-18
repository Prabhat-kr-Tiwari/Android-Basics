package com.prabhat.activitylifecycle

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

    var uri = mutableStateOf<Uri?>(null)
        private set

    fun updateUri(newuri: Uri) {
        uri.value = newuri
    }
}