package sps.notepad96.textfieldcustom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sps.notepad96.textfieldcustom.ui.theme.TextFieldCustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldCustomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    var text01 by remember { mutableStateOf("text01") }
    Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyBasicTextField(text = text01, setOnTextChange = { text01 = it })
    }
}

@Composable
fun MyBasicTextField(
    text: String,
    setOnTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text, onValueChange = setOnTextChange,
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(20))
            .size(width = 250.dp, height = 50.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20)),
        textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
//        cursorBrush =  Brush.verticalGradient(colors = listOf(Color(0xFF2193b0), Color(0xFF6dd5ed))),
        cursorBrush = SolidColor(Color.Green),
        decorationBox = { innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box {
                    if (text.isEmpty()) {
                        Text(text = "Enter Text", fontSize = 20.sp, color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        }
    )
}