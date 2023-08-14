package com.christianto.natalio.android.lab.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.christianto.natalio.android.lab.widgets.SearchAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onBackClicked: (String) -> Unit) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var active by rememberSaveable {
        mutableStateOf(false)
    }
    var recentSearches by rememberSaveable {
        mutableStateOf<List<String>>(listOf())
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        Column(modifier = Modifier.padding(it)) {
            SearchAppBar(
                text = text,
                active = active,
                recentSearches = recentSearches,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    active = false
                    if (!recentSearches.contains(text.trim())) {
                        recentSearches = recentSearches.toMutableList().apply { add(text.trim()) }
                    }
                    onBackClicked(text)
                    text = ""
                },
                onActiveChange = {
                    active = it
                },
                onClear = { text = "" }
            )
        }
    }
}