package com.example.chatapplication.splash

 sealed interface SplashEvents {
    data  object NavigateToHome : SplashEvents
    data  object NavigateToLogin : SplashEvents
 data object Idle : SplashEvents

}