package sps.notepad96.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import sps.notepad96.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    Scaffold(
        topBar = { MyTopBar() },
        content = {
            MyContents(it)
        },
        bottomBar = { MyBottomBar() },

        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        title = {
            Text(text = "My Top Bar")
        },
        // Left Side
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        // Right Side
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Info, contentDescription = "Info")
            }
        }
    )
}

@Composable
fun MyContents(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color(233, 233, 233, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Main Content", style = MaterialTheme.typography.headlineLarge)
    }
}

@Composable
fun MyBottomBar() {
    // 1번
    BottomAppBar(containerColor = Color(130, 240, 155, 255)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        }
    }

    // 2번
    val labels = listOf("Home", "Favorite", "Setting")
    val icons = listOf(Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Settings)
    var selected by remember { mutableIntStateOf(0) }
    NavigationBar {
        for (i in 0..2) {
            NavigationBarItem(selected = selected == i,
                onClick = { selected = i },
                icon = {
                    Icon(icons[i], contentDescription = labels[i])
                },
                label = {
                    Text(labels[i])
                })
        }
    }
}
