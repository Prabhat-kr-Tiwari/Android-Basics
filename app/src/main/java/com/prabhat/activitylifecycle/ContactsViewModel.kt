package com.prabhat.activitylifecycle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

//if we use this whenever we change the darktheme,change the lanaguage,rotate our device activity will be recreated and this will
//call again and change the color the default color that is white
//to solove this we need to retain these on configuration changes
/*class ContactsViewModel {

    var background by mutableStateOf(Color.White)
        private set
    fun changeBackgroundColor(){
        background = Color.Red

    }
}*/
//this viewmodel will create a component which will out live lifecycle  of its screen
//so that viewmodel will not be destroyed when activity will destroyed
//viewmodel will only when user activily pops the activity then it will
/*class ContactsViewModel: ViewModel() {

    var background by mutableStateOf(Color.White)
        private set
    fun changeBackgroundColor(){
        background = Color.Red

    }
}*/

class ContactsViewModel(val helloworld: String): ViewModel() {

    var background by mutableStateOf(Color.White)
        private set
    fun changeBackgroundColor(){
        background = Color.Red

    }
}

