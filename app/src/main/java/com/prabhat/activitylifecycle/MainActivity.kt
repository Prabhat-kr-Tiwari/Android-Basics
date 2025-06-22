package com.prabhat.activitylifecycle

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil3.asImage
import coil3.compose.AsyncImage
import com.prabhat.activitylifecycle.ui.theme.ActivityLifecycleTheme

class MainActivity : ComponentActivity() {
    lateinit var  workManager: WorkManager
    private val viewModel by viewModels<PhotoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        workManager = WorkManager.getInstance(applicationContext)
       val drawbale =  resources.getDrawable(R.drawable.main_before,null)
        setContent {
            ActivityLifecycleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val x =  innerPadding
                    val workresult = viewModel.workId?.let {id->
                        workManager.getWorkInfoByIdLiveData(id).observeAsState().value
                    }
                    LaunchedEffect(key1 = workresult?.outputData) {
                        if (workresult?.outputData!=null){
                            val filepath = workresult.outputData.getString(PhotoCompressionWorker.KEY_RESULT_PATH)
                            filepath?.let {
                                val bitmap = BitmapFactory.decodeFile(it)
                                viewModel.updateCompressedBitmap(bitmap)
                            }
                        }
                    }

                    Column {

                        viewModel.uncompressUri?.let {
                            Text("Uncompressed image")
                            AsyncImage( model = it, contentDescription = "")
                            Spacer(Modifier.height(20.dp))


                        }

                        viewModel.compressBitmap?.let {
                            Text("compressed image")
                            AsyncImage( model = it.asImageBitmap(), contentDescription = "")

                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT> Build.VERSION_CODES.TIRAMISU){
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        }else{
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)

        }?:return
        viewModel.updateUnCompressUri(uri = uri)
        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
            .setInputData(
                workDataOf(
                    PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
                    PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024*20L
                )
            ).setConstraints(
                Constraints(requiresStorageNotLow = true)
            ).build()

        viewModel.updateWorkId(request.id)
        workManager.enqueue(request = request)
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