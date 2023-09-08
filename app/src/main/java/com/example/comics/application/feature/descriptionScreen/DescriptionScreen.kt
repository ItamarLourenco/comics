package com.example.comics.application.feature.descriptionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.comics.R
import com.example.comics.application.feature.main.MainViewModel
import com.example.comics.application.feature.widget.HtmlTextView

@Composable
fun DescriptionScreen(
    itemId:String,
    mainViewModel: MainViewModel
) {
    val results by mainViewModel.itemsState.collectAsState(initial = emptyList())
    val result = results.find { it.id == itemId.toInt() }

    result?.let{item ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = "${item.thumbnail.path}.${item.thumbnail.extension}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier
                    .padding(top=16.dp, bottom=16.dp)
            )
            HtmlTextView(htmlText = item.description.toString())
        }
    } ?: run{
        Text(
            text = stringResource(id = R.string.item_not_found),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}
