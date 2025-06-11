package com.prabhat.activitylifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prabhat.activitylifecycle.ui.theme.ActivityLifecycleTheme

class MainActivity : ComponentActivity() {
    //this will not work because this will create a new instance whenever activity will be called


//    private val viewModel = ContactsViewModel()
    //android way of intializing the viewmodel in activity
//    private val viewModel by viewModels<ContactsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivityLifecycleTheme {
//compose way of using viewmodel
//                val viewModel = viewModel<ContactsViewModel>()

                //providing viewmodel factory if the viewmodel has constructor
                val viewModel = viewModel<ContactsViewModel>(
                    factory = object : ViewModelProvider.Factory{
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return ContactsViewModel(helloworld = "Hello world") as T
                        }
                    }

                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = viewModel.background
                ) {
                    Button(onClick = {
                        viewModel.changeBackgroundColor()
                    }) {

                        Text(text = "Button")
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActivityLifecycleTheme {
        Greeting("Android")
    }
}