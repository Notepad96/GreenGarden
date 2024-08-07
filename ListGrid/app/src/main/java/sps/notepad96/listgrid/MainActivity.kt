package sps.notepad96.listgrid

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
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
import sps.notepad96.listgrid.ui.theme.ListGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListGridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    var selected by remember { mutableIntStateOf(0) }
    val sample = mutableListOf<String>()
    for (i in 1..50) sample.add("$i:Text")

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
                Text(text = "Column Grid List")
            }
            Button(onClick = { selected = 1 }) {
                Text(text = "Row Grid List")
            }
        }

        when(selected) {
            0 -> {
                LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 200.dp)) {
                    itemsIndexed(sample) { index, text ->
                        ListGridItem(index, text)
                    }
                }
            }
            1 -> {
                LazyHorizontalGrid(rows = GridCells.Fixed(3)) {
                    items(50) { index ->
                        ListGridItem(index, "item")
                    }
                }
            }
        }
    }
}

@Composable
fun ListGridItem(index: Int, text: String) {
    val widths = listOf(150.dp, 200.dp, 250.dp)
    val colors = listOf(Color(222, 193, 240, 255),
        Color(221, 243, 181, 255),
        Color(172, 215, 243, 255)
    )
    Column(
        modifier = Modifier.width(widths.random()).height(100.dp).background(colors.random()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$text $index")
    }
}