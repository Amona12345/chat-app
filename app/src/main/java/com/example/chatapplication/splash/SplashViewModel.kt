package com.example.chatapplication.splash

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chatapplication.model.AppUser
import com.example.chatapplication.utills.FirebaseUtils
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class SplashViewModel : ViewModel() {
    val events = mutableStateOf<SplashEvents>( SplashEvents.Idle)
 val auth = Firebase.auth
    fun navigateToHome (user: AppUser){
        events.value = SplashEvents.NavigateToHome(user)
    }
    fun navigateToLogin (){
events.value = SplashEvents.NavigateToLogin
    }
    fun navigate(){

         auth.currentUser?.uid?.let {
             uid ->
             getUserFromFireStore(uid)
         }?:navigateToLogin()
        }


    fun getUserFromFireStore(uid: String) {
        FirebaseUtils.getUser(uid, onSuccessListener = {  documentSnapshot ->
            val user = documentSnapshot.toObject(AppUser::class.java)
         user?.let {
             navigateToHome(it)

         } ?: navigateToLogin()
        }, onFailureListener = {
            Log.e("TAG", "Error Occurred :${it.message}")
            navigateToLogin()
        })

    }

}