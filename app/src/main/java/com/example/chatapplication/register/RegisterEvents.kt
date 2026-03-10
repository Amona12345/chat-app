package com.example.chatapplication.register

import com.example.chatapplication.model.AppUser

sealed interface RegisterEvents {

     data  class NavigateToHome(val user: AppUser): RegisterEvents
        data object Idle : RegisterEvents


}