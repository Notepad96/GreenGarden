package sps.notepad96.dropdownmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sps.notepad96.dropdownmenu.ui.theme.DropdownMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DropdownMenuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
    var selectedItem by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Text(
            "Selected: ${selectedItem ?: ""}",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                MenuItem(item, onItemSelected = { selectedItem = it })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuItem(item: String, onItemSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        ListItem(headlineContent = { Text(item) },
            leadingContent = { Icon(Icons.Default.Done, "", tint = Color.Red) },
            modifier = Modifier.combinedClickable(
                onClick = {},
                onLongClick = { expanded = true  }
            ))

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = 150.dp, y = (-30).dp)
        ) {
            DropdownMenuItem(onClick = {
                onItemSelected("$item - Action 1")
                expanded = false
            },
                text = {
                    Text("Action 1")
                })
            DropdownMenuItem(onClick = {
                onItemSelected("$item - Action 2")
                expanded = false
            },
                text = {
                    Text("Action 2")
                })
        }
    }
}