package com.christianto.natalio.android.lab.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailsScreen(navController: NavController, movieTitle: String?) {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.LightGray,
            elevation = 8.dp
        ) {
            Icon(
                modifier = Modifier.clickable {
                    navController.popBackStack()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back"
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = "$movieTitle"
            )
        }
    }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                movieTitle?.let {
                    Text(text = it)
                }
            }
        }
    }
}