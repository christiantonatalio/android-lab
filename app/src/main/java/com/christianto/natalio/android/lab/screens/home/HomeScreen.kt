package com.christianto.natalio.android.lab.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.christianto.natalio.android.lab.model.Movie
import com.christianto.natalio.android.lab.model.getMovies
import com.christianto.natalio.android.lab.navigation.MovieScreen
import com.christianto.natalio.android.lab.ui.widgets.MovieItem

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.LightGray, elevation = 8.dp
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
    movieList: List<Movie> = getMovies()
) {
    Column {
        LazyColumn(modifier = modifier) {
            items(items = movieList) {
                MovieItem(
                    movie = it,
                    elevation = 8.dp
                ) { movieId ->
                    navController.navigate(route = MovieScreen.DetailsScreen.withArgs(movieId))
                }
            }
        }
    }
}