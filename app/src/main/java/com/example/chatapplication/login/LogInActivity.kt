package com.example.chatapplication.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapplication.R
import com.example.chatapplication.register.RegisterActivity
import com.example.chatapplication.login.ui.theme.ChatApplicationTheme
import com.example.chatapplication.login.ui.theme.black
import com.example.chatapplication.utills.ChatAuthButton
import com.example.chatapplication.utills.ChatAuthTextField
import com.example.chatapplication.utills.ChatTopBar

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatApplicationTheme {
                LoginContent()
            }
        }
    }

    @Composable
    fun LoginContent(viewModel: LoginViewModel = viewModel()) {
        Scaffold(topBar = {
            ChatTopBar("Login")
        }) { paddingValues ->
            paddingValues
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillWidth
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.4F))
                    Text(
                        text = "Welcome Back",
                        color = black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                    ChatAuthTextField(
                        state = viewModel.emailState,
                        viewModel.emailErrorState.value,
                        "Email"
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    ChatAuthTextField(
                        state = viewModel.passwordState,
                        viewModel.passwordErrorState.value,
                        "password"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ChatAuthButton(
                        title = "Login", onClick = { }, enabled = true,
                        modifier = Modifier
                            .fillMaxWidth(0.9F)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    TextButton(onClick = { viewModel.navigateToRegister()}, modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = " OR Create an account",
                            color = Color.Gray,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    }

                }
            }
         }
            TriggerEvents(viewModel.events.value)
        }
        @Composable
        fun TriggerEvents(event: LoginEvents, viewModel: LoginViewModel = viewModel()) {
            val context = LocalContext.current
            when (event) {
                is LoginEvents.NavigateToHome -> {

                }

                is LoginEvents.NavigateToRegister -> {
                    val intent = Intent(context, RegisterActivity::class.java)
                    context.startActivity(intent)
                    viewModel.resetEvents()
                }

                is LoginEvents.Idle -> {}
            }
        }
    }


