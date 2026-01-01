package com.shubhamkrtiwari.revisionadda.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.shubhamkrtiwari.revisionadda.R

@Composable
fun SplashScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier,
    splashDurationMs: Long = 1400
) {
    val scale = remember { Animatable(0.9f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
        )
        delay(splashDurationMs)
        onTimeout()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // simple vertical gradient background
        val brush = Brush.verticalGradient(listOf(Color(0xFFB3D7FF), Color(0xFF4A2BCC)))
        Box(modifier = Modifier
            .fillMaxSize()
            .background(brush))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Use your app logo resource (replace R.drawable.ic_books if different)
            val painter = runCatching { painterResource(id = R.drawable.ic_books) }.getOrNull()
            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = "RevisionAdda logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(120.dp)
                        .scale(scale.value)
                )
            } else {
                // fallback circle
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .scale(scale.value)
                        .background(Color.White, shape = CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "RevisionAdda",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your Complete Learning Companion",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}
