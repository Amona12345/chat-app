package com.example.chatapplication.splash

import com.example.chatapplication.model.AppUser

sealed interface SplashEvents {
     data  class NavigateToHome(val user : AppUser) : SplashEvents
    data  object NavigateToLogin : SplashEvents
 data object Idle : SplashEvents

}