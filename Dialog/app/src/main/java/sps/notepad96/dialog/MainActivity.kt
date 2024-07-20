package sps.notepad96.dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import sps.notepad96.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(innerPadding: PaddingValues) {
    var showDialog01 by remember { mutableStateOf(false) }
    var showDialog02 by remember { mutableStateOf(false) }

    if (showDialog01) {
        Dialog01(
            onDismissRequest = { showDialog01 = false },
            onConfirmation = { showDialog01 = false },
            dialogTitle = "대화 상자에 넣을 제목",
            dialogText = "대화 상자에 넣을 내용"
        )
    }
    if (showDialog02) {
        Dialog02(onDismissRequest = { showDialog02 = false },
            onConfirmation = { showDialog02 = false })
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { showDialog01 = true }) {
            Text(text = "AlertDialog")
        }
        Button(onClick = { showDialog02 = true }) {
            Text(text = "Dialog")
        }
    }
}

@Composable
fun Dialog01(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.Info, contentDescription = "")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(
                text = dialogText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onConfirmation() })
            {
                Text("확인", color = Color(250, 46, 46, 255))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() })
            {
                Text("취소")
            }
        }
    )
}

@Composable
fun Dialog02(onDismissRequest: () -> Unit, onConfirmation: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "자유롭게 텍스트나 이미지들을 넣어서 레이아웃 구성이 가능", Modifier.padding(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text("취소")
                    }
                    TextButton(onClick = { onConfirmation() }) {
                        Text("확인", color = Color(250, 46, 46, 255))
                    }
                }
            }
        }
    }
}