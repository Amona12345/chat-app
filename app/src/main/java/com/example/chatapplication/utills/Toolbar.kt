package com.example.chatapplication.utills

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(title: String, onNavigationIconClick: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            if (onNavigationIconClick != null) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "icon back",
                    modifier = Modifier.padding(8.dp).clickable{
                        onNavigationIconClick
                    }
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        ))

}