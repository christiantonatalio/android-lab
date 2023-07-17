package com.christianto.natalio.android.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christianto.natalio.android.lab.ui.theme.AndroidLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidLabTheme {
                // A surface container using the 'background' color from the theme
                AppUI()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun AppUI() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0XFF546E7A)
        ) {
            val amountState = remember {
                mutableStateOf(0)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$${amountState.value}",
                    style = TextStyle(
                        color = updateColorState(amountState.value),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                AddCircleButton(amountState.value) {
                    amountState.value = it
                }
            }
        }
    }
}

private fun updateColorState(amountState: Int) =
    if (amountState >= 1000) Color.Green else Color.White

@Composable
fun AddCircleButton(amount: Int, updateAmount: (Int) -> Unit) {
    Button(
        modifier = Modifier
            .padding(top = 32.dp)
            .size(120.dp),
        onClick = {
            updateAmount(amount + 100)
        },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    ) {
        Text("Tap")
    }
}