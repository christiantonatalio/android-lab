package com.christianto.natalio.android.lab.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christianto.natalio.android.lab.data.DataOrException
import com.christianto.natalio.android.lab.model.Weather
import com.christianto.natalio.android.lab.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    val data: MutableState<DataOrException<Weather, Boolean, Exception>> = mutableStateOf(
        DataOrException(
            null, true, Exception("")
        )
    )

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("jakarta")
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getWeather(city)
            data.value.loading = false
        }
    }
}