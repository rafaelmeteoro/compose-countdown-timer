package com.meteoro.compose_countdown_timer.view.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meteoro.compose_countdown_timer.R

@Composable
fun CountDownIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    time: String,
    size: Int,
    stroke: Int
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column(modifier = modifier) {
        Box {
            CircularProgressIndicatorBackGround(
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                color = colorResource(R.color.pink),
                stroke = stroke
            )

            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                color = colorResource(R.color.maroon),
                strokeWidth = stroke.dp
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = time,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)) ,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CircularProgressIndicatorBackGround(
    modifier: Modifier = Modifier,
    color: Color,
    stroke: Int
) {
    val style = with(LocalDensity.current) { Stroke(stroke.dp.toPx()) }

    Canvas(modifier = modifier, onDraw = {
        val innerRadius = (size.minDimension - style.width) / 2

        drawArc(
            color = color,
            startAngle = 0F,
            sweepAngle = 360F,
            topLeft = Offset(
                x = (size / 2.0F).width - innerRadius,
                y = (size / 2.0F).height - innerRadius
            ),
            size = Size(width = innerRadius * 2, height = innerRadius * 2),
            useCenter = false,
            style = style
        )
    })
}

@Preview
@Composable
private fun DefaultCountDownIndicator() {
    CountDownIndicator(progress = 20F, time = "00:20", size = 250, stroke = 4)
}