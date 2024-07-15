package sps.notepad96.button

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import sps.notepad96.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        MyButton01()
        MyButton02()
        MyButton03()
    }
}

@Composable
fun MyButton01() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(159, 241, 115, 255),
            contentColor = Color(37, 33, 32, 255)
        ),
        border = BorderStroke(3.dp, Color(51, 143, 255, 255)),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 15.dp)
    ) {
        Text(text = "Button 01")
    }
}

@Composable
fun MyButton02() {
    var num by remember { mutableIntStateOf(2) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextButton(
            onClick = { num += 1 },
        ) {
            Text(text = "Button 0${num}")
        }

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "Button 0${num}", modifier = Modifier.clickable(
                onClick = { num += 1 },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
        )
    }
}

@Composable
fun MyButton03() {
    var checked by remember { mutableStateOf(false) }
    OutlinedIconToggleButton(
        checked = checked, onCheckedChange = { checked = !checked },
        colors = IconButtonDefaults.outlinedIconToggleButtonColors(
            checkedContainerColor = Color(139, 253, 151, 255),
            checkedContentColor = Color.Red
        )
    )
    {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
    }
}