package com.christianto.natalio.android.lab.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.christianto.natalio.android.lab.model.Movie
import com.christianto.natalio.android.lab.model.getMovies
import com.christianto.natalio.android.lab.ui.widgets.MovieItem

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().firstOrNull {
        it.id == movieId
    }

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
                text = "Movie Details"
            )
        }
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            movie?.let { movie ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    MovieItem(movie = movie)
                    Text(text = "Movie Images")
                    HorizontalScrollableImageView(movie)
                }
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        movie.images?.let {
            items(items = it) { image ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(240.dp),
                    elevation = 4.dp
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = image),
                        contentDescription = "Movie Posters",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}