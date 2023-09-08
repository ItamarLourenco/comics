package com.example.comics.application.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.comics.application.feature.widget.HtmlTextView

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val results by mainViewModel.itemsState.collectAsState(initial = emptyList())
    Column() {
        LazyColumn (
            modifier = Modifier
                .padding(16.dp)
        ) {
            items(results) { item ->
                Row(
                    modifier = Modifier
                        .clickable {
                            navController.navigate("description_screen/${item.id}")
                        }
                        .padding(bottom=16.dp, top=16.dp)
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
