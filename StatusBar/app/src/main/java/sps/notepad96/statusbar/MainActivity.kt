package sps.notepad96.statusbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import sps.notepad96.statusbar.ui.theme.StatusBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatusBarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    Column(modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
        .background(Color(255, 194, 71, 255)),
        verticalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = { /*TODO*/ }) {
            Text("Hello Android")
        }
        Button(onClick = { /*TODO*/ }) {
            Text("Hello Android")
        }
    }
}