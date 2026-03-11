package com.example.chatapplication.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapplication.R
import com.example.chatapplication.login.ui.theme.blue
import com.example.chatapplication.register.RegisterEvents
import com.example.chatapplication.register.RegisterViewModel
import com.example.chatapplication.room.RoomActivity
import com.example.chatapplication.ui.theme.ChatApplicationTheme
import com.example.chatapplication.utills.ChatTopBar

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatApplicationTheme {
                HomeContent()
            }
        }
    }

    @Composable
    fun HomeContent(viewModel: HomeViewModel = viewModel()) {

        Scaffold(
            topBar = {
                ChatTopBar(title = "Chat App")
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.navigateToAddRoomScreen()

                }, shape = CircleShape, containerColor = blue, modifier = Modifier.size(60.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = "icon add room"
                    )
                }
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
                        .height(300.dp),
                    contentScale = ContentScale.FillWidth
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {


                }
            }
            TriggerEvents(viewModel.event.value) { }
        }
    }

    @Composable
    fun TriggerEvents(event: HomeEvents, viewModel: HomeViewModel = viewModel(),
                      onSuccessRegister: () ->Unit) {
        val context = LocalContext.current
        when (event) {
           HomeEvents.Idle -> {}
            is HomeEvents.NavigateToAddRoomScreen -> {
                val intent = Intent(context, RoomActivity::class.java)
                context.startActivity(intent)
                viewModel.resetEvenState()
            }
        }

}}
