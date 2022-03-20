package com.lnsantos.dojo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lnsantos.dojo.cards.RenderListNames
import com.lnsantos.dojo.ui.theme.BackgroundItemNameNight
import com.lnsantos.dojo.ui.theme.DojoTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DojoTheme { DefaultPreview() }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CardIndicator(
    text: String,
    onClickListener: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        onClick = onClickListener
    ) {
        Card(
            backgroundColor = BackgroundItemNameNight
        ) {
            Text(
                text = text.toUpperCase(Locale.ROOT),
                modifier = Modifier.padding(
                    start = Dp(32f),
                    top = Dp(16f),
                    bottom = Dp(24f)
                ),
                color = Color.White,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Light)
            )
        }
    }
}

const val DOJO_01_LAZY_COLUMNS = 1

@Composable
private fun MyApp() {
    val names = mutableListOf("Lucas", "Felipe", "Bruno", "Carlos")
    val item = List(150) { "$it" }
    val canShowOnBoarding = rememberSaveable { mutableStateOf(0) }

    when (canShowOnBoarding.value) {
        DOJO_01_LAZY_COLUMNS -> RenderListNames(item)
        else -> {
            Column(
                modifier = Modifier.fillMaxWidth().padding(Dp(16f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CardIndicator(text = "Cards example") { canShowOnBoarding.value = 1 }
            }
        }
    }

}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun DefaultPreview() {
    MyApp()
}