package com.prabhat.activitylifecycle

import android.content.Context
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {

//    not store activity context in the viewmodel it leads to memory leak
    //because viewmodel out live the activity lifecycl

     var context:Context?=null




}