package sps.notepad96.textfield

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import sps.notepad96.textfield.ui.theme.TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    var text01 by remember { mutableStateOf("") }
    var text02 by remember { mutableStateOf("123xxx") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(text = "text01 : $text01")
            MyTextField(text = text01, setOnTextChange = { text01 = it })
        }
        Column {
            Text(text = "text02 : $text02")
            MyOutlinedTextField(text = text02, setOnTextChange = { text02 = it })
        }
    }
}

@Composable
fun MyTextField(
    text: String,
    setOnTextChange: (String) -> Unit
) {
    TextField(
        value = text, onValueChange = { setOnTextChange(it) },
        colors = TextFieldDefaults.colors(
//            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Green,
            unfocusedIndicatorColor = Color.Blue
        ),
        textStyle = TextStyle(textAlign = TextAlign.End, fontSize = 20.sp)
    )
}

@Composable
fun MyOutlinedTextField(
    text: String,
    setOnTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text, onValueChange = { setOnTextChange(it) },
        label = { Text(text = "Name") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = !text.isDigitsOnly(),
        trailingIcon = {
            if (!text.isDigitsOnly()) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "error",
                    tint = MaterialTheme.colorScheme.error)
            }
        },
        supportingText = {
            if (!text.isDigitsOnly()) {
                Text(text = "Only Number!!", fontSize = 14.sp)
            }
        }
    )
}
