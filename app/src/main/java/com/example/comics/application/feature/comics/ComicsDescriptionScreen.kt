package com.example.comics.application.feature.comics

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.comics.R
import com.example.comics.application.feature.widget.HtmlTextView
import com.example.comics.ui.theme.ComicsTheme

@Composable
fun ComicsDescriptionScreen(
    navController: NavHostController,
    params: Bundle?,
    comicsViewModel: ComicsViewModel,
) {
    val result = comicsViewModel.getItemById(itemId = params?.getString("itemId"))

    ComicsTheme {
        result?.let{item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = item.image,
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
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(LocalContext.current.getString(R.string.back))
                }
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

}
