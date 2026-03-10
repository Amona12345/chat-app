package com.example.chatapplication.utills

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatapplication.R
import com.example.chatapplication.login.ui.theme.blue

@Composable
fun ChatAuthButton(title: String, onClick: () -> Unit, enabled: Boolean,
                   modifier: Modifier = Modifier) {
    Button(
        modifier = if (enabled)modifier else modifier.shadow(spotColor = Color.Black,
            shape = RoundedCornerShape(6.dp), elevation =5.dp ),
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) {
                blue
            } else {
              Color.White
            },
            contentColor = if (enabled) {
          Color.White
            } else {
             Color.Gray
            }
        ),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(text = title, fontSize = 14.sp)
        Spacer(modifier= Modifier.weight(1F))
        Image(
            painter = painterResource(
                id = if (enabled) {
                    R.drawable.baseline_arrow_forward_24
                } else {
                    R.drawable.baseline_arrow_forward_black_24
                }
            ), contentDescription = "icon forward"
        )
    }

}