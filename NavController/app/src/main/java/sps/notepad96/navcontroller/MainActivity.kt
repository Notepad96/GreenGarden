package sps.notepad96.navcontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sps.notepad96.navcontroller.ui.theme.NavControllerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavControllerTheme {
                NavFunc()
            }
        }
    }
}

sealed class NavRoutes(val name: String) {
    object Main: NavRoutes("main")
    object Home: NavRoutes("home")
    object Profile: NavRoutes("profile")
}

@Composable
fun NavFunc() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.Main.name) {
        composable(NavRoutes.Main.name) {
            Main(nav = navController)
        }
        composable(NavRoutes.Home.name) {
            Home(nav = navController)
        }
        composable(NavRoutes.Profile.name + "/{msg}") {
            val msg = it.arguments?.getString("msg")
            Profile(nav = navController, msg)
        }
    }
}

@Composable
fun Main(nav: NavController) {
    var text by remember { mutableStateOf("Start") }
    val onChangeText = { v: String ->
        text = v
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { nav.navigate(NavRoutes.Home.name) }) {
                    Text(text = "Home")
                }
                Button(onClick = { nav.navigate(NavRoutes.Profile.name + "/$text") }) {
                    Text(text = "Profile")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextField(value = text, onValueChange = onChangeText)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(nav: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Home") },
        navigationIcon = {
            IconButton(onClick = { nav.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "back")
            }
        })
    })
    {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(Color(240, 148, 148, 255)),
            contentAlignment = Alignment.Center
        ) {
            Text("Home", fontSize = 28.sp)
        }
    }

}

@Composable
fun Profile(nav: NavController, msg: String?) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(240, 238, 148, 255)),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {
            Text("Profile", fontSize = 28.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text("msg = $msg", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { nav.navigateUp() }) {
                Text(text = "확인")
            }
        }
    }
}