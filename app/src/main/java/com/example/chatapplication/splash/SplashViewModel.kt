package com.example.chatapplication.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    val events = mutableStateOf<SplashEvents>( SplashEvents.Idle)

    fun navigateToHome (){
        events.value = SplashEvents.NavigateToHome
    }
    fun navigateToLogin (){
events.value = SplashEvents.NavigateToLogin
    }

}