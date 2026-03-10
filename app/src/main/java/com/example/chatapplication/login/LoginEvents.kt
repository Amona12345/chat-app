package com.example.chatapplication.login

import com.example.chatapplication.splash.SplashEvents

sealed interface LoginEvents {
    data  object NavigateToHome : LoginEvents
    data  object NavigateToRegister : LoginEvents
    data object Idle : LoginEvents

}