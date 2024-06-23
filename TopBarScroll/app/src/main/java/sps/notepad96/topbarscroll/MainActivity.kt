package sps.notepad96.topbarscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sps.notepad96.topbarscroll.ui.theme.TopBarScrollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopBarScrollTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()  // 맨 위 혹은 위로 스크롤 할 때 나타남
//    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior() // 맨 위인 경우만 나타남
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior() // 화면 상단의 고정

    Scaffold(
        topBar = { TopBar(scrollBehavior = scrollBehavior) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(40) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(horizontal = 4.dp),
                        onClick = { /*TODO*/ },
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Card $index")
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        scrollBehavior = scrollBehavior, // 변수 연결
        title = {
            Text(
                text = "TopBar Scroll Test",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Green,
            scrolledContainerColor = Color.Yellow   // 스크롤 중 TopBar 배경색
        )
    )
}