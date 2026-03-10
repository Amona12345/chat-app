package com.example.chatapplication.utills

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.chatapplication.login.ui.theme.blue
import com.google.android.gms.common.ErrorDialogFragment

@Composable
fun LoadingDialog(isLoading: MutableState<Boolean>) {
    if(isLoading.value){
        Dialog(onDismissRequest = {isLoading.value=false}) {
            CircularProgressIndicator(color = blue, modifier = Modifier
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
                .padding(36.dp))

        }
    }
}

@Composable
fun ErrorDialog(mess : MutableState<String>) {
    if(mess.value.isNotEmpty()){
        AlertDialog(onDismissRequest = {mess.value = ""}, confirmButton = {
            TextButton(onClick = {mess.value = ""}) {
                Text(text = "OK")

            }
        },
            text={
                Text(text = mess.value)
            } )
    }
}