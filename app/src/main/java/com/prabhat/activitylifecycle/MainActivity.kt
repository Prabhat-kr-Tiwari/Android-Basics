package com.prabhat.activitylifecycle

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.prabhat.activitylifecycle.ui.theme.ActivityLifecycleTheme
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val uri = Uri.parse("android.resource://$packageName/drawable/MainBefore")
        val mainbeforebyte = contentResolver.openInputStream(uri)?.use {
            it.readBytes()
        }
        println("mainbefore size  = ${mainbeforebyte?.size}")
        val file = File(filesDir, "MainBefore.jpeg")
        FileOutputStream(file).use {
            it.write(mainbeforebyte)
        }
        println(file.toURI())
        setContent {
            ActivityLifecycleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val x = innerPadding

                    val pickImage = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = {contentUri->
                            print("Content uri $contentUri")

                        }
                    )
                    Button(onClick = {
                        pickImage.launch("image/*")
                    }) {

                        Text("Pick image")
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