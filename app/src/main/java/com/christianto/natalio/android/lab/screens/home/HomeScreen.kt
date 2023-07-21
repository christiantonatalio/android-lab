package com.christianto.natalio.android.lab.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.christianto.natalio.android.lab.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Magenta, elevation = 8.dp
        ) {
            Text(text = "Movies")
        }
    }) {
        MainContent(modifier = Modifier.padding(it), navController)
    }
}


@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieList: List<String> = listOf("Avatar", "300", "Harry Potter", "Life")
) {
    Column {
        LazyColumn(modifier = modifier) {
            items(items = movieList) {
                MovieItem(movie = it) {
                    navController.navigate(route = MovieScreens.DetailsScreen.name)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: String, onItemClicked: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(100.dp)
            .clickable {
                onItemClicked(movie)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Movie Image"
            )
            Text(text = movie)
        }
    }
}