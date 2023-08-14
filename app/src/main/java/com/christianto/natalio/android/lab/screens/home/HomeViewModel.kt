package com.christianto.natalio.android.lab.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christianto.natalio.android.lab.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    val city = MutableStateFlow("")
    init {
        city.update { "jakarta" }
    }

    val weatherUiState: StateFlow<HomeUiState> = city.map { city ->
        val result = repository.getWeather(city)
        when {
            result.e != null -> {
                HomeUiState.Error(result.e?.message.toString())
            }
            else -> {
                result.data?.let {
                    HomeUiState.Success(it)
                } ?: HomeUiState.Empty
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeUiState.Loading
    )

    fun updateCity(newCity: String) {
        city.update { newCity }
    }
}