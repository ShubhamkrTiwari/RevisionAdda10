package com.shubhamkrtiwari.revisionadda.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.ui.Alignment
import kotlin.math.roundToInt

@Composable
fun ProgressCard(
    percent: Float, // 0.0 .. 1.0
    chapters: Pair<Int, Int>, // done / total
    questions: Int,
    correct: Int,
    modifier: Modifier = Modifier
) {
    val animated = animateFloatAsState(targetValue = percent.coerceIn(0f, 1f))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier
            .background(color = Color(0xFF2E243B))
            .padding(18.dp)
        ) {
            Text(text = "Overall Progress", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularPercent(percent = animated.value)
            }
            Spacer(modifier = Modifier.height(14.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StatPill(title = "Chapters", big = "${chapters.first}/${chapters.second}", color = Color(0xFF2A6F9E))
                StatPill(title = "Questions", big = "$questions", color = Color(0xFF3C8A45))
                StatPill(title = "Correct", big = "$correct", color = Color(0xFF8E5AC1))
            }
        }
    }
}

@Composable
private fun CircularPercent(percent: Float) {
    val size = 140.dp
    Box(modifier = Modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = Stroke(width = 16f, cap = StrokeCap.Round)
            // background circle
            drawArc(
                color = Color.White.copy(alpha = 0.08f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = stroke
            )
            // progress arc
            drawArc(
                color = Color(0xFF82B1FF),
                startAngle = -90f,
                sweepAngle = 360f * percent,
                useCenter = false,
                style = stroke
            )
        }
        Text(text = "${(percent * 100).roundToInt()}%", color = Color.White, fontSize = 26.sp)
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun StatPill(title: String, big: String, color: Color) {
    Surface(
        modifier = Modifier
            .width(100.dp)
            .height(68.dp)
            .clip(RoundedCornerShape(12.dp)),
        color = color.copy(alpha = 0.14f)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = big, color = color, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = title, color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
        }
    }
}
