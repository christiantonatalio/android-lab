package com.christianto.natalio.android.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.christianto.natalio.android.lab.ui.theme.AndroidLabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    AndroidLabTheme {
        // A surface container using the 'background' color from the theme
        content()
    }
}

@Composable
fun MainContent() {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Text(text = "Hello Android Lab!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MainContent()
    }
}