package com.meteoro.compose_countdown_timer.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meteoro.compose_countdown_timer.R
import com.meteoro.compose_countdown_timer.utils.Utility
import com.meteoro.compose_countdown_timer.utils.Utility.formatTime
import com.meteoro.compose_countdown_timer.view.components.CountDownButton
import com.meteoro.compose_countdown_timer.view.components.CountDownIndicator
import com.meteoro.compose_countdown_timer.view.components.ShowCelebration
import com.meteoro.compose_countdown_timer.viewmodel.MainViewModel

@Composable
fun CountDownView(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val time by viewModel.time.observeAsState(Utility.TIME_COUNTDOWN.formatTime())
    val progress by viewModel.progress.observeAsState(1.00F)
    val isPlaying by viewModel.isPlaying.observeAsState(false)
    val celebrate by viewModel.celebrate.observeAsState(false)

    CountDownView(
        time = time,
        progress = progress,
        isPlaying = isPlaying,
        celebrate = celebrate
    ) {
        viewModel.handleCountDownTimer()
    }
}

@Composable
fun CountDownView(
    time: String,
    progress: Float,
    isPlaying: Boolean,
    celebrate: Boolean,
    optionSelected: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (celebrate) {
            ShowCelebration()
        }

        Text(
            text = "Timer",
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            fontFamily = FontFamily(Font(R.font.poppins_semibold))
        )

        Text(
            text = "1 minute to launch...",
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            fontFamily = FontFamily(Font(R.font.poppins_semibold))
        )

        Text(
            text = "Click to start or stop countdown",
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )

        CountDownIndicator(
            modifier = Modifier.padding(top = 50.dp),
            progress = progress,
            time = time,
            size = 250,
            stroke = 12
        )

        CountDownButton(
            modifier = Modifier
                .padding(top = 70.dp)
                .size(70.dp),
            isPlaying = isPlaying,
        ) {
            optionSelected()
        }
    }
}

@Preview
@Composable
fun DefaultCountDownView() {
    CountDownView(
        time = "01:33",
        progress = 10f,
        isPlaying = false,
        celebrate = false,
        optionSelected = { }
    )
}