package com.lnsantos.dojo.cards

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.lnsantos.dojo.extension.dpByState
import com.lnsantos.dojo.extension.iconExpandByState
import com.lnsantos.dojo.extension.onInverterValue
import com.lnsantos.dojo.extension.stringByState
import com.lnsantos.dojo.ui.theme.BackgroundScreen


@Preview(showBackground = true)
@Composable
private fun previewTest() {
    RenderListNames(mutableListOf("Lucas", "Felipe", "Bruno", "Carlos"))
}

@Composable
fun RenderListNames(myList: List<String> = List(150) { "$it" }) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(myList) { item ->
            CardIndicator(name = item)
        }
    }
}

@Composable
private fun <T : MutableList<String>> T.showCards() {
    Column {
        for (name in this@showCards) {
            CardIndicator(name = name)
        }
    }
}

private fun getSettingSpringAnimation() = spring<Dp>(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow
)

@Composable
private fun CardIndicator(name: String) {

    val isExpanded = remember { mutableStateOf(false) }

    val columnPadding by animateDpAsState(
        targetValue = isExpanded.dpByState(16f, 0f),
        animationSpec = getSettingSpringAnimation()
    )

    Card(
        modifier = Modifier
            .padding(
                vertical = Dp(4f),
                horizontal = Dp(30f)
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(
                    bottom = columnPadding.coerceAtLeast(Dp(0f)),
                    top = Dp(16f),
                    start = Dp(16f),
                    end = Dp(16f)
                )
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Hello,"
                )
                Text(
                    text = "$name!",
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
                )

                if (isExpanded.value){

                    Spacer(modifier = Modifier.padding(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .clip(RectangleShape)
                            .background(BackgroundScreen)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "13 actionable tasks: 5 executed, 8 up-to-date 13 actionable tasks: 5 executed, 8 up-to-date "
                    )

                }

            }

            IconButton(
                onClick = { isExpanded.onInverterValue() },
            ) {
                Icon(
                    imageVector = isExpanded.iconExpandByState(),
                    contentDescription = isExpanded.stringByState("Show less", "Show More")
                )
            }
        }

    }
}