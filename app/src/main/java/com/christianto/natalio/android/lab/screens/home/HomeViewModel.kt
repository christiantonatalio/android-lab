package com.christianto.natalio.android.lab.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christianto.natalio.android.lab.data.DataOrException
import com.christianto.natalio.android.lab.model.Favorite
import com.christianto.natalio.android.lab.model.Weather
import com.christianto.natalio.android.lab.repository.WeatherRepository
import com.christianto.natalio.android.lab.screens.favorite.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherFlow = MutableStateFlow<DataOrException<Weather, Boolean, Exception>?>(null)
    private val _favoriteEvent = MutableSharedFlow<FavoriteState>()

    init {
        viewModelScope.launch {
            _favoriteEvent
                .debounce(500L)
                .collect { event ->
                    when (event) {
                        is FavoriteState.Add -> {
                            repository.addFav(event.favorite)
                        }

                        is FavoriteState.Delete -> {
                            repository.deleteFav(event.favorite)
                        }
                    }
                }
        }
        fetchWeatherData("jakarta")
    }

    val uiState: StateFlow<HomeUiState> = combine(
        _weatherFlow,
        repository.getAllFavorites()
    ) { currentWeather, favoriteCities ->
        deriveUiState(currentWeather, favoriteCities)
    }.stateIn(viewModelScope, SharingStarted.Lazily, HomeUiState.Loading)

    private fun deriveUiState(
        currentWeather: DataOrException<Weather, Boolean, Exception>?,
        favoriteCities: List<Favorite>
    ): HomeUiState {
        return when {
            currentWeather?.e != null -> {
                HomeUiState.Error(currentWeather.e?.message.toString())
            }

            else -> {
                currentWeather?.data?.let { weatherData ->
                    val isFavorite =
                        favoriteCities.any { it.city == weatherData.city?.name }
                    HomeUiState.Success(weatherData, isFavorite)
                } ?: HomeUiState.Empty
            }
        }
    }

    fun fetchWeatherData(city: String) {
        viewModelScope.launch {
            val weatherData = repository.getWeather(city)
            _weatherFlow.emit(weatherData)
        }
    }

    fun onFavoriteClicked(city: String, country: String, isFavoriteState: Boolean) {
        viewModelScope.launch {
            if (isFavoriteState) {
                _favoriteEvent.emit(FavoriteState.Add(Favorite(city, country)))
            } else _favoriteEvent.emit(FavoriteState.Delete(Favorite(city, country)))
        }
    }
}