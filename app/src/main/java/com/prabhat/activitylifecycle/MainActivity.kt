package com.prabhat.activitylifecycle

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.prabhat.activitylifecycle.ui.theme.ActivityLifecycleTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ImageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val drawbale = resources.getDrawable(R.drawable.main_before, null)
        setContent {
            ActivityLifecycleTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    viewModel.uri.let {

                        AsyncImage(model = viewModel.uri, contentDescription = "")
                    }


                    Button(onClick = {
                        /*     val intent = Intent(applicationContext, SecondActivity::class.java)
                             startActivity(intent)*/


                        //launch youtube app
                        /*  Intent(Intent.ACTION_MAIN).also {
                              it.`package`="com.google.android.youtube"
                              try {
                                  startActivity(it)
                              }catch (e: Exception){
                                  e.printStackTrace()
                              }

                          }*/

                        //implicit intent
                       val intent =  Intent(Intent.ACTION_SEND).apply {
                            type="text/plain"
                            putExtra(Intent.EXTRA_EMAIL,arrayOf("test@gmail"))
                            putExtra(Intent.EXTRA_SUBJECT,"this is my subject")
                            putExtra(Intent.EXTRA_TEXT,"tHIS IS MY EMAIL")
                        }
                        if (intent.resolveActivity(packageManager)!=null){
                            startActivity(intent)
                        }
                    }) {
                        Text("CLick me")
                    }

                }
            }
        }
    }

    //if launch mode is singletop this will be called
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM,Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.updateUri(uri!!)
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