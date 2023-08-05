package com.christianto.natalio.android.lab.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.christianto.natalio.android.lab.data.DataOrException
import com.christianto.natalio.android.lab.model.Weather
import com.christianto.natalio.android.lab.widgets.WeatherAppBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val weather = viewModel.data.value

    if (weather.loading == true) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                WeatherAppBar(
                    title = "${weather.data?.city?.name}, ${weather.data?.city?.country}",
                    elevation = 3.dp,
                    icon = Icons.Default.ArrowBack
                )
            }
        ) {
            HomeContent(
                modifier = Modifier.padding(it),
                data = weather
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    data: DataOrException<Weather, Boolean, Exception>
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            Text(text = "${data.data?.city?.name}")
        }
    }
}