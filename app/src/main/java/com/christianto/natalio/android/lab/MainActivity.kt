package com.christianto.natalio.android.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.christianto.natalio.android.lab.screen.QuizHome
import com.christianto.natalio.android.lab.ui.theme.AndroidLabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                QuizHome()
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        QuizHome()
    }
}