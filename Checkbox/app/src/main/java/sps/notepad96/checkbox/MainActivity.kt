package sps.notepad96.checkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sps.notepad96.checkbox.ui.theme.CheckboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckboxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    val sample = listOf("Meat", "Bread", "Snack", "Drink")
    val checkList = remember { mutableStateListOf(false, false, false, false) }
    val allChecked = when {
        checkList.all { it } -> ToggleableState.On
        checkList.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(start = 100.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Select Food", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "All Select", fontSize = 20.sp)
            TriStateCheckbox(state = allChecked, onClick = {
                val newState = allChecked != ToggleableState.On
                checkList.forEachIndexed { index, _ ->
                    checkList[index] = newState
                }
            })
        }

        sample.forEachIndexed { index, s ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Checkbox(checked = checkList[index], onCheckedChange = {
                    checkList[index] = it
                })
                Spacer(modifier = Modifier.width(8.dp))
                ClickableText(text = AnnotatedString(s, SpanStyle(fontSize = 18.sp))) {
                    checkList[index] = !checkList[index]
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Selected : ${checkList.withIndex().filter { it.value }.map { sample[it.index] }}",
            fontSize = 18.sp
        )
    }
}