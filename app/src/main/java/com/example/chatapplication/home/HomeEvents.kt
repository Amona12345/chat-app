package com.example.chatapplication.home

sealed interface HomeEvents {
    data object Idle : HomeEvents
    data object NavigateToAddRoomScreen: HomeEvents

}