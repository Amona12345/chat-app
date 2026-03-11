package com.example.chatapplication.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
      val event =mutableStateOf<HomeEvents>(HomeEvents.Idle)
    fun navigateToAddRoomScreen (){
        event.value = HomeEvents.NavigateToAddRoomScreen

    }
    fun resetEvenState(){
        event.value = HomeEvents.Idle

    }
}