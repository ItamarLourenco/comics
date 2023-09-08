package com.example.comics.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comics.ui.theme.ComicsTheme

data class ListItem(val title: String, val icon: ImageVector, val description: String)
@Composable
fun ListWithIconCompose(name: String, modifier: Modifier = Modifier) {
    val items = listOf(
        ListItem("Oi Manolo hi hi", Icons.Default.Favorite, "Description 1"),
        ListItem("agora mudei o numero 2", Icons.Default.ThumbUp, "Description 2"),
        ListItem("Item 3", Icons.Default.Info, "Description 3"),
        ListItem("Item 4", Icons.Default.Lock, "Description 4"),
        ListItem("Item 5", Icons.Default.Send, "Description 5")
    )


    LazyColumn {
        items(items) { item ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = item.icon, contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = item.title, fontWeight = FontWeight.Bold)
                    Text(text = item.description)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComicsTheme {
        ListWithIconCompose("Android manolo")
    }
}