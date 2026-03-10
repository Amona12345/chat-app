package com.example.chatapplication.register

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapplication.HomeActivity
import com.example.chatapplication.R
import com.example.chatapplication.ui.theme.ChatApplicationTheme
import com.example.chatapplication.utills.LoadingDialog
import com.example.chatapplication.utills.ChatAuthButton
import com.example.chatapplication.utills.ChatAuthTextField
import com.example.chatapplication.utills.ChatTopBar
import com.example.chatapplication.utills.ErrorDialog

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatApplicationTheme {
                RegisterContent (onRegisterSuccess = {
                    finishAffinity()
                }, onFinish = {
                    finish()
                })
            }
        }
    }

    @Composable
    fun RegisterContent(viewModel: RegisterViewModel = viewModel(),onFinish: () -> Unit,
                        onRegisterSuccess: () -> Unit) {
        Scaffold(
            topBar = {
                ChatTopBar(title = "register") {
                    onFinish
                }
            }
        ) { paddingValues ->
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
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.49F))
                    ChatAuthTextField(viewModel.firstNameState,
                        viewModel.firstNameErrorState.value,
                        "firstName")
                    Spacer(modifier = Modifier.padding(4.dp))

                    ChatAuthTextField(viewModel.emailState,
                        viewModel.emailErrorState.value,
                        "email")
                    Spacer(modifier = Modifier.padding(4.dp))
                    ChatAuthTextField(viewModel.passwordState,
                        viewModel.passwordErrorState.value,
                        "password", isPassword = true)
                    Spacer(modifier = Modifier.weight(1F))
                    ChatAuthButton(
                        title = "register", onClick = {
                            viewModel.register()
                        }, enabled = false,
                        modifier = Modifier
                            .fillMaxWidth(0.9F)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(32.dp))

                }
            }
            TriggerEvents(viewModel.events.value){
                onRegisterSuccess
            }
            LoadingDialog(viewModel.isLoading)
            ErrorDialog(viewModel.message)
        }
    }
    @Composable
    fun TriggerEvents(event: RegisterEvents, viewModel: RegisterViewModel = viewModel(),
                      onSuccessRegister: () ->Unit) {
        val context = LocalContext.current
        when (event) {
            RegisterEvents.Idle -> {}
            is RegisterEvents.NavigateToHome -> {

                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
                onSuccessRegister()
            }
        }
    }
}