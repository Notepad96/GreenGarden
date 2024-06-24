package sps.notepad96.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sps.notepad96.list.ui.theme.ListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    val sample = listOf("Text 01", "Text 02", "Text 03", "Text 04", "Text 05", "Text 06", "Text 07", "Text 08")
    var selected by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { selected = 0 }) {
                Text(text = "Column List")
            }
            Button(onClick = { selected = 1 }) {
                Text(text = "Row List")
            }
        }

        when (selected) {
            0 -> {
                LazyColumn {
                    item {
                        ListHeader()
                    }
                    items(sample) { text ->
                        ListItem(text = text)
                    }
                    item {
                        ListFooter()
                    }
                }
            }

            1 -> {
                LazyRow {
                    item {
                        ListHeader()
                    }
                    itemsIndexed(sample) { index, text ->
                        ListItem(text = text, index = index)
                    }
                    item {
                        ListFooter()
                    }
                }
            }
        }
    }
}

@Composable
fun ListHeader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Header", fontSize = 24.sp)
    }
}

@Composable
fun ListItem(text: String, index: Int = 0) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(150.dp)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "text = $text | index = $index")
        }
    }
}

@Composable
fun ListFooter() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Footer", fontSize = 24.sp)
    }
}