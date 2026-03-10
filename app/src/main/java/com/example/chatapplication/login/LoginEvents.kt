package com.example.chatapplication.login

import com.example.chatapplication.model.AppUser
import com.example.chatapplication.splash.SplashEvents

sealed interface LoginEvents {
    data  class NavigateToHome(val user : AppUser) : LoginEvents
    data  object NavigateToRegister : LoginEvents
    data object Idle : LoginEvents


}