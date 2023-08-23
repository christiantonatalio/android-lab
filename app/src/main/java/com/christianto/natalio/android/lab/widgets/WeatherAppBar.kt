package com.christianto.natalio.android.lab.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    modifier: Modifier = Modifier,
    title: String = "Title",
    isMainScreen: Boolean = true,
    isFavorite: Boolean = false,
    elevation: Dp = 0.dp,
    onBackClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onFavoriteClicked: (Boolean) -> Unit = {},
    onOptionClicked: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        modifier = modifier.shadow(elevation = elevation),
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onOptionClicked() }) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "More Icon"
                    )
                }
            }
        },
        navigationIcon = {
            if (!isMainScreen) {
                IconButton(onClick = { onBackClicked() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back Icon",
                    )
                }
            } else {
                Row {
                    if (isFavorite) {
                        IconButton(
                            onClick = { onFavoriteClicked(false) }) {
                            Icon(
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = "Favorite Icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { onFavoriteClicked(true) }) {
                            Icon(
                                imageVector = Icons.Rounded.FavoriteBorder,
                                contentDescription = "Favorite Icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    IconButton(onClick = { onSearchClicked() }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    modifier: Modifier = Modifier,
    recentSearches: List<String> = listOf(),
    text: String = "",
    active: Boolean = false,
    onQueryChange: (String) -> Unit = {},
    onSearch: () -> Unit = {},
    onActiveChange: (Boolean) -> Unit = {},
    onClear: () -> Unit = {}
) {
    DockedSearchBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        query = text,
        onQueryChange = { onQueryChange(it) },
        onSearch = { onSearch() },
        active = active,
        onActiveChange = { onActiveChange(it) },
        placeholder = {
            Text("Search city")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Icon",
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.isEmpty()) {
                            onActiveChange(false)
                        } else onClear()
                    },
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close Icon",
                )
            }
        }
    ) {
        recentSearches.forEach {
            it.let {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .clickable { onQueryChange(it) }) {
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = "History Icon"
                    )
                    Text(it)
                }
            }
        }
    }
}