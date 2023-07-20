package com.christianto.natalio.android.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.christianto.natalio.android.lab.ui.components.InputField
import com.christianto.natalio.android.lab.ui.theme.AndroidLabTheme
import com.christianto.natalio.android.lab.ui.widgets.RoundedIconButton
import com.christianto.natalio.android.lab.util.calculateTipAmount
import com.christianto.natalio.android.lab.util.calculateTotalPerPerson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    val amount = remember {
                        mutableStateOf(0.0)
                    }
                    TopHeader(amount.value)
                    MainContent(
                        modifier = Modifier
                            .padding(top = 16.dp)
                    ) {
                        amount.value = it
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    AndroidLabTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .padding(16.dp),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopHeader(amount: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
        color = Color(0XFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total = "%.2f".format(amount)
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.h5
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContent(modifier: Modifier = Modifier, updateTotalAmount: (Double) -> Unit = {}) {
    val splitNumber = remember {
        mutableStateOf(1)
    }
    val tipAmount = remember {
        mutableStateOf(0.0)
    }
    val totalPerPerson = remember {
        mutableStateOf(0.0)
    }
    BillForm(
        modifier = modifier,
        splitNumber = splitNumber,
        tipAmount = tipAmount,
        totalPerPerson = totalPerPerson
    )
    updateTotalAmount(totalPerPerson.value)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    splitNumber: MutableState<Int>,
    tipAmount: MutableState<Double>,
    totalPerPerson: MutableState<Double>
) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val isValidState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val tipPercentage = remember {
        mutableStateOf(0.0f)
    }
    val formattedTipePercentage = (tipPercentage.value * 100).toInt()
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                modifier = Modifier
                    .fillMaxWidth(),
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (isValidState.not()) return@KeyboardActions
                    keyboardController?.hide()
                    totalPerPerson.value = calculateTotalPerPerson(
                        totalBillState.value.toDouble(),
                        splitNumber.value,
                        formattedTipePercentage
                    )
                }
            )

            if (isValidState) {

                // Split Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Split",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Row(
                        modifier = Modifier
                    ) {
                        RoundedIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {
                                splitNumber.value = if (splitNumber.value > 1) --splitNumber.value
                                else 1
                                totalPerPerson.value = calculateTotalPerPerson(
                                    totalBillState.value.toDouble(),
                                    splitNumber.value,
                                    formattedTipePercentage
                                )
                            }
                        )
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = "${splitNumber.value}"
                        )
                        RoundedIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                                splitNumber.value = ++splitNumber.value
                                totalPerPerson.value = calculateTotalPerPerson(
                                    totalBillState.value.toDouble(),
                                    splitNumber.value,
                                    formattedTipePercentage
                                )
                            }
                        )
                    }
                }

                // Tip Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tip",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Text(
                        text = "$${tipAmount.value}",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 8.dp),
                    text = "$formattedTipePercentage%"
                )

                Slider(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    value = tipPercentage.value,
                    onValueChange = {
                        tipPercentage.value = it
                        tipAmount.value = calculateTipAmount(
                            totalBillState.value.toDouble(),
                            formattedTipePercentage
                        )
                        totalPerPerson.value = calculateTotalPerPerson(
                            totalBillState.value.toDouble(),
                            splitNumber.value,
                            formattedTipePercentage
                        )
                    },
                    steps = 5
                )
            }
        }
    }
}
