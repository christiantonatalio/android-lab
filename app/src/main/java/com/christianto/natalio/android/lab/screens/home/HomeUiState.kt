package com.christianto.natalio.android.lab.screens.home

import com.christianto.natalio.android.lab.model.Weather

sealed class HomeUiState {
    data class Success(val data: Weather) : HomeUiState()
    data class Error(val errorMessage: String) : HomeUiState()
    object Loading : HomeUiState()
    object Empty : HomeUiState()
}