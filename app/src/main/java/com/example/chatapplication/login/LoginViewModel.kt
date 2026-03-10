package com.example.chatapplication.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chatapplication.splash.SplashEvents

class LoginViewModel: ViewModel(){
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val emailErrorState = mutableStateOf<String?>(null)
    val passwordErrorState = mutableStateOf<String?>(null)
    val events = mutableStateOf<LoginEvents>( LoginEvents.Idle)

fun resetEvents(){
    events.value = LoginEvents.Idle
}

    fun navigateToRegister (){
        events.value = LoginEvents.NavigateToRegister
    }

}