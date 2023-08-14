package com.christianto.natalio.android.lab.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.christianto.natalio.android.lab.R
import com.christianto.natalio.android.lab.model.WeatherDetails
import com.christianto.natalio.android.lab.model.WeatherX
import com.christianto.natalio.android.lab.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onSearchClicked: () -> Unit = {},
    onOptionsClicked: () -> Unit = {},
    city: String?
) {
    city?.let {
        viewModel.updateCity(city)
    }
    val weatherResponse = viewModel.weatherUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    when (weatherResponse.value) {
        is HomeUiState.Error -> {
            Log.d("INILIO", (weatherResponse.value as HomeUiState.Error).errorMessage)
        }
        is HomeUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is HomeUiState.Empty -> {}
        else -> {
            val weather = (weatherResponse.value as HomeUiState.Success).data
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    WeatherAppBar(
                        title = "${weather.city?.name}, ${weather.city?.country}",
                        scrollBehavior = scrollBehavior,
                        onOptionClicked = { onOptionsClicked() },
                        onSearchClicked = { onSearchClicked() }
                    )
                }
            ) {
                HomeContent(
                    modifier = Modifier.padding(it),
                    data = weather.list
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    data: List<WeatherDetails>?
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        data?.getOrNull(0)?.let {
            item {
                WeatherToday(it)
                data.forEach { weather ->
                    ForecastItem(weather)
                }
            }
        }
    }
}

@Composable
fun WeatherToday(weather: WeatherDetails) {
    Text(
        text = weather.getFormattedDate(),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.SemiBold
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherIcon(icon = weather.getWeatherIcon(), size = 100.dp)
            Text(
                text = weather.getTemperature(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "${weather.getWeatherCondition()}",
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

    HumidityWindPressureRow(weather)
    Divider()
    SunriseSunsetRow(weather)
}

@Composable
private fun WeatherIcon(
    size: Dp = 80.dp,
    icon: String
) {
    Image(
        painter = rememberAsyncImagePainter(model = icon),
        contentDescription = "Weather Image",
        modifier = Modifier.size(size)
    )
}

@Composable
fun SunriseSunsetRow(weather: WeatherDetails?) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp),
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
            )
            Text(text = "${weather?.getSunriseTime()}")
        }

        Text(
            text = "|",
            modifier = Modifier.padding(horizontal = 8.dp),
            color = DividerDefaults.color
        )

        Row {
            Text(text = "${weather?.getSunsetTime()}")
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherDetails?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
            )
            Text(text = "${weather?.humidity} %")
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
            )
            Text(text = "${weather?.pressure} psi")
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather?.getFormattedSpeed()} mph")
        }
    }
}


@Composable
fun ForecastItem(weather: WeatherDetails) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = weather.getFormattedDay())
            WeatherIcon(icon = weather.getWeatherIcon(), size = 50.dp)
            Text(text = weather.getWeatherDescription().orEmpty())
            Text(text = "${weather.getMinTemperature()}—${weather.getMaxTemperature()}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    val weather = WeatherDetails(
        weather = listOf(
            WeatherX(
                id = 500,
                main = "Rain",
                description = "light rain",
                icon = "10d"
            )
        )
    )
    HomeContent(
        data = listOf(
            weather, weather, weather, weather
        )
    )
}