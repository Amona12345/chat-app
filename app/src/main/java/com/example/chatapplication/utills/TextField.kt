package com.example.chatapplication.utills

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Label
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.chatapplication.login.ui.theme.blue

@Composable
fun ChatAuthTextField(state: MutableState<String>, error: String?, label :String ,trailingIcon :Int? = null) {
    Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {

        TextField(
            value = state.value, onValueChange = { newText ->
                state.value = newText
            }, modifier = Modifier.fillMaxWidth(0.9F),
            label = {
                Text(text = label ,fontSize = 12.sp)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Gray,
                unfocusedLabelColor = blue,
                errorIndicatorColor = Color.Red
            ),
            trailingIcon = {
                if(trailingIcon!=null){
                    Image(painter = painterResource(id = trailingIcon), contentDescription = "icon")
                }
            }
        )
        if(error!=null){
            Text(text = error, color = Color.Red, fontSize = 16.sp)
        }
    }
    }