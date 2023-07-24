package com.christianto.natalio.android.lab.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.christianto.natalio.android.lab.model.Movie
import com.christianto.natalio.android.lab.model.getMovies


@Preview
@Composable
fun MovieItem(
    movie: Movie = getMovies()[0],
    elevation: Dp = 0.dp,
    onItemClicked: (String) -> Unit = {}
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .clickable {
                movie.id?.let {
                    onItemClicked(it)
                }
            },
        shape = RoundedCornerShape(8.dp),
        elevation = elevation
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier

                    .size(100.dp),
                shape = RectangleShape,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.images?.get(0) ?: "").apply {
                                crossfade(true)
                            }.build()
                    ),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                movie.apply {
                    Text(text = movie.title.toString(), style = MaterialTheme.typography.h6)
                    Text(
                        text = "Director: ${movie.director.toString()}",
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = "Released: ${movie.year.toString()}",
                        style = MaterialTheme.typography.caption
                    )
                }

                AnimatedVisibility(visible = expanded) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 12.sp
                            )){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )){
                                append(movie.plot)
                            }
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Icon(
                    imageVector = if (!expanded) {
                        Icons.Filled.KeyboardArrowDown
                    } else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }
        }
    }
}