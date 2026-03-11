package com.example.chatapplication.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapplication.home.HomeActivity
import com.example.chatapplication.R
import com.example.chatapplication.login.LogInActivity
import com.example.chatapplication.ui.theme.ChatApplicationTheme
import com.example.chatapplication.utills.Constants

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatApplicationTheme {
                SplashContent {
                    finish()
                }
            }
        }
    }
}


@Composable
fun SplashContent(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = viewModel(),
    onFinish: () -> Unit,
) {
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.navigate()
        }, 2000)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Icon Image of Chat Application",
            modifier = Modifier.fillMaxHeight(0.24F)
        )

    }
    TriggerEvents(event = viewModel.events.value) {
        onFinish()
    }

}

@Composable
fun TriggerEvents(
    event: SplashEvents,
    viewModel: SplashViewModel = viewModel(),
    onFinish: () -> Unit
) {
    val context = LocalContext.current
    when (event) {
        is SplashEvents.NavigateToHome -> {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(Constants.USER_KEY, event.user)
            context.startActivity(intent)
            onFinish()
        }

        is SplashEvents.NavigateToLogin -> {
            val intent = Intent(context, LogInActivity::class.java)
            context.startActivity(intent)
            onFinish()
        }

        else -> {}
    }

}
