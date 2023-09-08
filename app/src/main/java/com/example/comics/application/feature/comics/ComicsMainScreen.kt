package com.example.comics.application.feature.comics

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.comics.application.feature.widget.HtmlTextView
import com.example.comics.ui.theme.ComicsTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ComicsMainScreen(
    navController: NavHostController,
    comicsViewModel: ComicsViewModel
) {
    val result by comicsViewModel.comicsState.collectAsState(initial = emptyList())
    val isLoading by comicsViewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    val error by comicsViewModel.errorState.collectAsState(initial = null)
    if(error !== null && result.isEmpty()){
        Toast.makeText(LocalContext.current, error, Toast.LENGTH_LONG).show()
    }

    ComicsTheme{
        Column(
        ) {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = comicsViewModel::fetchItems,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                LazyColumn (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()

                ) {
                    items(result) { item ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("comics_description_screen/${item.id}")
                                }
                                .padding(bottom = 16.dp, top = 16.dp)
                        ) {
                            AsyncImage(
                                model = "${item.thumbnail.path}.${item.thumbnail.extension}",
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(120.dp)
                            )
                            Column () {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                )
                                HtmlTextView(
                                    htmlText = item.description.toString(),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                        }
                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier.height(1.dp)
                        )
                    }
                }
            }
        }
    }
}
