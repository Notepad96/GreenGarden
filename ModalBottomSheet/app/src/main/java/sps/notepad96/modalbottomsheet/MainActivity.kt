package sps.notepad96.modalbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import sps.notepad96.modalbottomsheet.ui.theme.ModalBottomSheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModalBottomSheetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    var showSheet by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val onDismissRequest = { value: Boolean ->
        showSheet = value
    }
    MySheet(showSheet, text, onDismissRequest)

    Row(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            text = "Item 01"
            showSheet = true
        }) {
            Text(text = "Item 01")
        }
        Button(onClick = {
            text = "Item 02"
            showSheet = true
        }) {
            Text(text = "Item 02")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySheet(showSheet: Boolean, text: String, onDismissRequest: (Boolean) -> Unit) {
    if (showSheet) {
        ModalBottomSheet(onDismissRequest = { onDismissRequest(false) }) {
            ListItem(
                overlineContent = { Text(text = text) },
                headlineContent = { Text(text = "Modify") },
                leadingContent = { Icon(imageVector = Icons.Filled.Edit, contentDescription = "") })
            ListItem(
                supportingContent = { Text(text = text)},
                headlineContent = { Text(text = "Delete") },
                leadingContent = { Icon(imageVector = Icons.Filled.Delete, contentDescription = "") })
        }
    }
}